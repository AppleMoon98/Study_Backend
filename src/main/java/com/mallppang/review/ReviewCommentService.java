package com.mallppang.review;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

import com.mallppang.base.CommentService;
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
public class ReviewCommentService implements CommentService<ReviewCommentDTO>{
	private final ReviewCommentRepository commentRepository;
	private final ReviewCommentMapper mapper;
	private final ReviewRepository reviewRepository;
	private final MemberRepository memberRepository;	
	
	@Override
	public Long register(ReviewCommentDTO commentDTO, Long boardId){
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
		
		ReviewComment review = mapper.dtoToEntity(commentDTO);
		review.setCreateDate(LocalDateTime.now());
		review.setBoard(reviewRepository.getById(boardId));
		review.setMember(member);
		
		return commentRepository.save(review).getId();
	}
	


	@Override
	public List<ReviewCommentDTO> getList(Long boardId){
		List<ReviewComment> entityList = commentRepository.getList(boardId);
		List<ReviewCommentDTO> dtoList = new ArrayList<ReviewCommentDTO>();
		for(int i = 0; i < entityList.size(); i++)
			dtoList.add(mapper.entityToDTO(entityList.get(i)));
		return dtoList;
	}

	@Override
	public void modify(ReviewCommentDTO dto) {
		Optional<ReviewComment> result = commentRepository.findById(dto.getId());
		ReviewComment comment = result.orElseThrow();
		comment.setContent(dto.getContent());
		commentRepository.save(comment);
	}

	@Override
	public void delete(Long id) {
		commentRepository.updateToDelete(id, true);
	}
}
