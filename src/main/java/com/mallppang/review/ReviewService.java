package com.mallppang.review;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mallppang.bakery.Bakery;
import com.mallppang.bakery.BakeryRepository;
import com.mallppang.base.BaseService;
import com.mallppang.base.BoardImage;
import com.mallppang.base.PageRequestDTO;
import com.mallppang.base.PageResponseDTO;
import com.mallppang.member.MemberDTO;
import com.mallppang.member.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class ReviewService implements BaseService<ReviewDTO> {
	private final ReviewRepository reviewRepository;
	private final ReviewMapper mapper;
	private final MemberRepository memberRepository;
	private final BakeryRepository bakeryRepository;

	@Override
	public PageResponseDTO<ReviewDTO> getList(PageRequestDTO pageRequestDTO) {
		Pageable pageable = PageRequest.of(pageRequestDTO.getPage() - 1, pageRequestDTO.getSize(),
				Sort.by("id").descending());

		Page<Object[]> result = reviewRepository.selectList(pageable);
		List<ReviewDTO> dtoList = result.get().map(arr -> {
			ReviewBoard reviewBoard = (ReviewBoard) arr[0];
			BoardImage boardImage = (BoardImage) arr[1];

//			ReviewDTO reviewDTO = mapper.entityToDTO(reviewBoard);
			ReviewDTO reviewDTO = ReviewDTO.builder().id(reviewBoard.getId()).title(reviewBoard.getTitle())
					.content(reviewBoard.getContent()).createDate(reviewBoard.getCreateDate())
					.delFlag(reviewBoard.isDelFlag()).writer(reviewBoard.getMember().getNickname())
					.email(reviewBoard.getMember().getEmail()).build();

			if (boardImage != null)
				reviewDTO.setUploadFileNames(List.of(boardImage.getFileName()));

			return reviewDTO;
		}).collect(Collectors.toList());

		Long totalCount = result.getTotalElements();
		return PageResponseDTO.<ReviewDTO>withAll().dtoList(dtoList).totalCount(totalCount)
				.pageRequestDTO(pageRequestDTO).build();
	}

	@Override
	public Long register(ReviewDTO reviewDTO) {
		var auth = SecurityContextHolder.getContext().getAuthentication();

		// 비로그인은 댓글 금지
		if (auth == null || auth instanceof AnonymousAuthenticationToken)
			throw new AuthenticationCredentialsNotFoundException("로그인이 필요합니다");

		String email = null;
		Object p = auth.getPrincipal();

		// 회원 정보 찾아오기
		if (p instanceof MemberDTO memberDTO)

			email = memberDTO.getEmail();
		else if (p instanceof User userData)
			email = userData.getUsername();
		else if (p instanceof String s && !"anonymousUser".equals(s))
			email = s;

		// 회원정보가 없을 때
		if (email == null || email.isBlank())
			throw new AuthenticationCredentialsNotFoundException("로그인이 필요합니다");

		// 이메일 찾아보고 없으면 null 처리
		var member = memberRepository.getWithRoles(email);
		if (member == null)
			throw new IllegalStateException("해당 이메일로 등록된 회원을 찾을 수 없습니다 : " + email);

		Bakery bakery = bakeryRepository.findById(reviewDTO.getBakeryId()).get();
		if (bakery != null)
			reviewDTO.setBakeryId(bakery.getId());

		ReviewBoard review = mapper.dtoToEntity(reviewDTO);
		review.setMember(member);
		review.setCreateDate(LocalDateTime.now());// 작성 날짜 지정 (현재 날짜 및 시간)
		review.setBakery(bakery);

		return reviewRepository.save(review).getId(); // UID
	}

	@Override
	public ReviewDTO get(Long id) {
		Optional<ReviewBoard> result = reviewRepository.selectOne(id);
		ReviewBoard reviewBoard = result.orElseThrow();
		ReviewDTO reviewDTO = mapper.entityToDTO(reviewBoard);

		if (reviewBoard.getBakery() != null) {
			reviewDTO.setBakeryId(reviewBoard.getBakery().getId());
		}
		return reviewDTO;
	}

	@Override
	public void modify(ReviewDTO reviewDTO) {
		Optional<ReviewBoard> result = reviewRepository.findById(reviewDTO.getId());
		ReviewBoard review = result.orElseThrow();

		review.setTitle(reviewDTO.getTitle());
		review.setContent(reviewDTO.getContent());
		review.clearImageList();

		List<String> uploadFileNames = reviewDTO.getUploadFileNames();

		if (uploadFileNames != null && uploadFileNames.size() > 0)
			uploadFileNames.stream().forEach(uploadName -> review.addImageString(uploadName));

		if (reviewDTO.getBakeryId() != null) {
			Bakery bakery = bakeryRepository.findById(reviewDTO.getBakeryId()).orElse(null);
			// 값이 null이 아니면 bakeryRepository에서 아이디를 찾아온다
			//orElse가 붙는 이유는 bakeryRepository가 Optional을 사용하기 때문
			if (bakery != null) {
				review.setBakery(bakery);
			}
		}

		reviewRepository.save(review);
	}

	public void delete(Long id) {
		reviewRepository.updateToDelete(id, true);
	}
}
