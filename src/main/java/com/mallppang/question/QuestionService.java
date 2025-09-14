package com.mallppang.question;

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
public class QuestionService implements BaseService<QuestionDTO> {
	private final QuestionRepository questionRepository;
	private final QuestionMapper mapper;
	private final MemberRepository memberRepository;

	@Override
	public Long register(QuestionDTO questionDTO) {
		var auth = SecurityContextHolder.getContext().getAuthentication();
		
		
		if(auth == null || auth instanceof AnonymousAuthenticationToken) {
			throw new AuthenticationCredentialsNotFoundException("Login required");
		}
		
		String email = null;
		Object p = auth.getPrincipal();
		
		if (p instanceof MemberDTO me)
			email= me.getEmail();
		else if(p instanceof User ud)
			email = ud.getUsername();
		else if(p instanceof String s && !"anonymousUser".equals(s))
			email = s;
		if(email == null || email.isBlank())
			throw new AuthenticationCredentialsNotFoundException("Login required");
		
		var member = memberRepository.getWithRoles(email);
		if(member == null)
			throw new IllegalStateException("해당 이메일을 등록한 회원을 찾을 수 없습니다 : " + email);
	
		
		Question question = mapper.dtoToEntity(questionDTO);
		question.setMember(member);
		question.setCreateDate(LocalDateTime.now());
		
		return questionRepository.save(question).getId();
	
	}

	@Override
	public QuestionDTO get(Long id) {
		Optional<Question> result = questionRepository.selectOne(id);
		Question question = result.orElseThrow();
		QuestionDTO questionDTO = mapper.entityToDTO(question);
		System.err.println();
		return questionDTO;
	}

	@Override
	public void modify(QuestionDTO questionDTO) {
		Optional<Question> result = questionRepository.findById(questionDTO.getId());
		Question question = result.orElseThrow();

		question.setTitle(questionDTO.getTitle());
		question.setContent(questionDTO.getContent());
		question.clearImageList();

		List<String> uploadFileNames = questionDTO.getUploadFileNames();
		
		if (uploadFileNames != null && uploadFileNames.size() > 0)
			uploadFileNames.stream().forEach(uploadFileName -> question.addImageString(uploadFileName));

		questionRepository.save(question);
	}

	@Override
	public void delete(Long id) {
		questionRepository.updateToDelete(id, true);
	}

	@Override
	public PageResponseDTO<QuestionDTO> getList(PageRequestDTO pageRequestDTO) {
		Pageable pageable = PageRequest.of(pageRequestDTO.getPage() - 1, pageRequestDTO.getSize(),
				Sort.by("id").descending());
		Page<Object[]> result = questionRepository.selectList(pageable);
		
		List<QuestionDTO> dtList = result.get().map(arr -> {
			Question question = (Question) arr[0];
			BoardImage questionImage = (BoardImage) arr[1];

			QuestionDTO questionDTO = QuestionDTO.builder().id(question.getId()).title(question.getTitle()).content(question.getContent()).createDate(question.getCreateDate())
					.build();

			if (questionImage != null)
				questionDTO.setUploadFileNames(List.of(questionImage.getFileName()));

			return questionDTO;
		}).collect(Collectors.toList());
		Long totalCount = result.getTotalElements();

		return PageResponseDTO.<QuestionDTO>withAll().dtoList(dtList).totalCount(totalCount).pageRequestDTO(pageRequestDTO)
				.build();
	}
}