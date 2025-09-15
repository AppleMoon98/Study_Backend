package com.mallppang.freeboard;

import org.springframework.stereotype.Component;

import com.mallppang.base.CommentMapper;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class FreeCommentMapper extends CommentMapper{
	public FreeCommentDTO entityToDTO(FreeComment entity) {
		FreeCommentDTO dto = FreeCommentDTO.builder().boardId(entity.getBoard().getId()).build();
		super.entityToDTO(entity, dto);
		
		if(entity.getMember() != null)
			dto.setWriter(entity.getMember().getNickname());
		System.err.println(entity.getMember());
		
		return dto;
	}
	
	public FreeComment dtoToEntity(FreeCommentDTO dto) {
		FreeComment entity = FreeComment.builder().build();
		super.dtoToEntity(dto, entity);
		return entity;
	}
}
