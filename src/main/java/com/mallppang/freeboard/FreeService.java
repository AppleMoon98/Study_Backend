package com.mallppang.freeboard;

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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.userdetails.User;

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
public class FreeService implements BaseService<FreeDTO> {
	private final FreeRepository boardRepository;
	private final FreeMapper mapper;
	private final MemberRepository memberRepository;

	@Override
	public Long register(FreeDTO freeDTO) {
		var auth = SecurityContextHolder.getContext().getAuthentication();

	    // 비로그인 차단
	    if (auth == null || auth instanceof AnonymousAuthenticationToken) 
	        throw new AuthenticationCredentialsNotFoundException("로그인이 필요합니다.");

	    String email = null;
	    Object p = auth.getPrincipal();

	    // 어떻게든 회원 정보 찾아서 가져옴
	    if (p instanceof MemberDTO memberDTO) 
	        email = memberDTO.getEmail();
	    else if (p instanceof User userData) 
	        email = userData.getUsername();
	    else if (p instanceof String s && !"anonymousUser".equals(s)) 
	        email = s;
	    
	    // 근데 없어?
	    if (email == null || email.isBlank()) 
	        throw new AuthenticationCredentialsNotFoundException("로그인이 필요합니다.");
	    
	    // 마지막으로 이메일 찾기, 없으면 null
	    var member = memberRepository.getWithRoles(email);
	    if (member == null) 
	        throw new IllegalStateException("해당 이메일을 등록한 회원을 찾을 수 없습니다 : " + email);
	    

	    FreeBoard free = mapper.dtoToEntity(freeDTO);
	    free.setMember(member);
	    free.setCreateDate(LocalDateTime.now());	// 작성날짜 지정

	    return boardRepository.save(free).getId();  //UID
	}

	@Override
	public FreeDTO get(Long id) {
		Optional<FreeBoard> result = boardRepository.selectOne(id);
		FreeBoard free = result.orElseThrow();
		FreeDTO freeDTO = mapper.entityToDTO(free);
		return freeDTO;
	}

	@Override
	public void modify(FreeDTO freeDTO) {
		Optional<FreeBoard> result = boardRepository.findById(freeDTO.getId());
		FreeBoard free = result.orElseThrow();

		free.setTitle(freeDTO.getTitle());
		free.setContent(freeDTO.getContent());
		free.clearImageList();

		List<String> uploadFileNames = freeDTO.getUploadFileNames();

		if (uploadFileNames != null && uploadFileNames.size() > 0)
			uploadFileNames.stream().forEach(uploadName -> free.addImageString(uploadName));

		boardRepository.save(free);
	}

	@Override
	public void delete(Long id) {
		// 여기서 소프트 리스트에서 제거되는 이유는
		// 애초에 쿼리문에서 안 들어오게 WHERE를 걸어뒀기 때문
		boardRepository.updateToDelete(id, true);
	}

	@Override
	public PageResponseDTO<FreeDTO> getList(PageRequestDTO pageRequestDTO) {
		Pageable pageable = PageRequest.of(pageRequestDTO.getPage() - 1, pageRequestDTO.getSize(),
				Sort.by("id").descending());
		Page<Object[]> result = boardRepository.selectList(pageable);

		List<FreeDTO> dtoList = result.get().map(arr -> {
			FreeBoard free = (FreeBoard) arr[0];
			BoardImage freeImage = (BoardImage) arr[1];

			// 문득 든 생각인데 빌더 왜씀? 매퍼 만들었잖아
//			FreeDTO freeDTO = FreeDTO.builder().id(free.getId()).title(free.getTitle()).content(free.getContent())
//					.createDate(free.getCreateDate()).build();	// 작성날짜 끌고오는 부분
			FreeDTO freeDTO = mapper.entityToDTO(free);

			if (freeImage != null)
				freeDTO.setUploadFileNames(List.of(freeImage.getFileName()));

			return freeDTO;
		}).collect(Collectors.toList());
		Long totalCount = result.getTotalElements();

		return PageResponseDTO.<FreeDTO>withAll().dtoList(dtoList).totalCount(totalCount).pageRequestDTO(pageRequestDTO)
				.build();
	}
}
