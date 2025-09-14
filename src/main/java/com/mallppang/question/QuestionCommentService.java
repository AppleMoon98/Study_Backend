package com.mallppang.question;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mallppang.base.CommentService;
import com.mallppang.base.PageRequestDTO;
import com.mallppang.base.PageResponseDTO;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class QuestionCommentService implements CommentService<QuestionCommentDTO>{
	private final QuestionCommentRepository commentRepository;
	private final QuestionCommentMapper mapper;
	
	@Override
	public Long register(QuestionCommentDTO dto) {
		QuestionComment question = mapper.dtoToEntity(dto);
		question.setCreateDate(LocalDateTime.now());
		QuestionComment result = commentRepository.save(question);
		return result.getId();
	}
	
	@Override
	public void modify(QuestionCommentDTO dto) {
		Optional<QuestionComment> result = commentRepository.findById(dto.getId());
		QuestionComment Question = result.orElseThrow();
		Question.setContent(dto.getContent());
		commentRepository.save(Question);
	}
	
	@Override
	public void delete(Long id) {
		// soft del
		commentRepository.updateToDelete(id, true);
	}
	
	@Override
	public PageResponseDTO<QuestionCommentDTO> getList(PageRequestDTO pageRequestDTO) {
		// 만약에 댓글 Size 수정한다면 여기를 수정할 것
		int size = 30;
		
		Pageable pageable = PageRequest.of(pageRequestDTO.getPage() - 1, size, Sort.by("id"));
		Page<Object> result = commentRepository.selectList(pageable);
		
		List<QuestionCommentDTO> dtoList = result.get().map(arr -> {
			QuestionComment comment = (QuestionComment) arr;
			QuestionCommentDTO dto = QuestionCommentDTO.builder().id(comment.getId()).content(comment.getContent())
					.boardId(comment.getBoard().getId()).build();
			return dto;
		}).collect(Collectors.toList());
		
		Long totalCount = result.getTotalElements();
		return PageResponseDTO.<QuestionCommentDTO>withAll().dtoList(dtoList).totalCount(totalCount)
				.pageRequestDTO(pageRequestDTO).build();
			
}
}