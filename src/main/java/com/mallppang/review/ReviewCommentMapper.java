package com.mallppang.review;

import org.springframework.stereotype.Component;
import com.mallppang.base.CommentMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ReviewCommentMapper extends CommentMapper{
	private final ReviewRepository repository;
	
	public ReviewComment dtoToEntity(ReviewCommentDTO dto){
		ReviewComment entity = ReviewComment.builder().build();
		super.dtoToEntity(dto, entity);
		entity.setBoard(repository.findById(dto.getBoardId()).get());
		return entity;
	}
	
	public ReviewCommentDTO entityToDTO(ReviewComment entity){
		ReviewCommentDTO dto = ReviewCommentDTO.builder().build();
		super.entityToDTO(entity, dto);
		return dto;
	}
}
