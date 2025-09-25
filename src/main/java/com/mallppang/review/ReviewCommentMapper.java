package com.mallppang.review;

import org.springframework.stereotype.Component;

import com.mallppang.base.CommentMapper;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ReviewCommentMapper extends CommentMapper{
	
	public ReviewCommentDTO entityToDTO(ReviewComment entity){
		ReviewCommentDTO dto = ReviewCommentDTO.builder().boardId(entity.getBoard().getId()).build();
		super.entityToDTO(entity, dto);
		
		if(entity.getMember() !=null){
			dto.setWriter(entity.getMember().getNickname());
			dto.setEmail(entity.getMember().getEmail());
		}
		return dto;
	}
	// 여기서 get이 루프를 일으킬 수 있다길래 삭제함 
	// 또 똑같은 문제 발생시 다시 작성
	public ReviewComment dtoToEntity(ReviewCommentDTO dto){
		ReviewComment entity = ReviewComment.builder().build();
		super.dtoToEntity(dto, entity);
		return entity;
	}
	
}
