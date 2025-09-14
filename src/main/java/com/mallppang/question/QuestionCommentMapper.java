package com.mallppang.question;

import org.springframework.stereotype.Component;

import com.mallppang.base.CommentMapper;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class QuestionCommentMapper extends CommentMapper{
	private final QuestionRepository questionRepository;
	
	public QuestionCommentDTO entityToDTO(QuestionComment entity) {
		QuestionCommentDTO dto = QuestionCommentDTO.builder().build();
		super.entityToDTO(entity, dto);
		
		
		
		return dto;
	}

	public QuestionComment dtoToEntity(QuestionCommentDTO dto) {
		QuestionComment entity = QuestionComment.builder().build();
		super.dtoToEntity(dto, entity);
		entity.setBoard(questionRepository.findById(dto.getBoardId()).get());
		return entity;
	}

}
