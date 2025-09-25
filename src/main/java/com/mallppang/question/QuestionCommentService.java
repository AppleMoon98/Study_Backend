package com.mallppang.question;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.mallppang.base.CommentService;
import com.mallppang.member.MemberDTO;
import com.mallppang.member.MemberRepository;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class QuestionCommentService implements CommentService<QuestionCommentDTO>{
	private final QuestionCommentRepository commentRepository;
	private final QuestionRepository questionRepository;
	private final QuestionCommentMapper mapper;
	private final MemberRepository memberRepository;
	
	@Override
	public Long register(QuestionCommentDTO dto, Long boardId) {
        var auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth == null || auth instanceof AnonymousAuthenticationToken)
			throw new AuthenticationCredentialsNotFoundException("로그인이 필요합니다.");
		
		String email = null;
		Object p = auth.getPrincipal();
		if (p instanceof MemberDTO memberDTO) 
	        email = memberDTO.getEmail();
	    else if (p instanceof User userData) 
	        email = userData.getUsername();
	    else if (p instanceof String str && !"anonymousUser".equals(str)) 
	        email = str;
		
		if(email == null || email.isBlank())
			throw new AuthenticationCredentialsNotFoundException("로그인이 필요합니다.");

		var member = memberRepository.getWithRoles(email);
		if(member == null)
			throw new IllegalStateException("해당 이메일을 등록한 회원을 찾을 수 없습니다.");
        
		QuestionComment question = mapper.dtoToEntity(dto);
		question.setCreateDate(LocalDateTime.now());
		question.setBoard(questionRepository.getById(boardId));
		question.setMember(member);
		
		return commentRepository.save(question).getId();
	}
	
	@Override
	public void modify(QuestionCommentDTO dto) {
		Optional<QuestionComment> result = commentRepository.findById(dto.getId());
		QuestionComment comment = result.orElseThrow();
		comment.setContent(dto.getContent());
		commentRepository.save(comment);
	}
	
	@Override
	public void delete(Long id) {
		commentRepository.updateToDelete(id, true);
	}
	
	public List<QuestionCommentDTO> getList(Long boardId){
		List<QuestionComment> entityList = commentRepository.getList(boardId);
		List<QuestionCommentDTO> dtoList = new ArrayList<QuestionCommentDTO>();
		for(int i = 0; i < entityList.size(); i++)
			dtoList.add(mapper.entityToDTO(entityList.get(i)));
		return dtoList;
	}
}
