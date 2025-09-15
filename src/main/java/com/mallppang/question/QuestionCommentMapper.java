package com.mallppang.question;

import org.springframework.stereotype.Component;

import com.mallppang.base.CommentMapper;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class QuestionCommentMapper extends CommentMapper{
	public QuestionCommentDTO entityToDTO(QuestionComment entity) {
		QuestionCommentDTO dto = QuestionCommentDTO.builder().boardId(entity.getBoard().getId()).build();
		super.entityToDTO(entity, dto);
		
		if(entity.getMember() != null)
			dto.setWriter(entity.getMember().getNickname());
		System.err.println(entity.getMember());
		
		return dto;
	}
	
	public QuestionComment dtoToEntity(QuestionCommentDTO dto) {
		QuestionComment entity = QuestionComment.builder().build();
		super.dtoToEntity(dto, entity);
		return entity;
	}
}
