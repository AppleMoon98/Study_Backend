package com.mallppang.question;

import org.springframework.stereotype.Component;

import com.mallppang.base.BoardImage;
import com.mallppang.base.BoardMapper;

@Component
public class QuestionMapper extends BoardMapper{
	
	public QuestionDTO entityToDTO(Question entity) {
		QuestionDTO dto = QuestionDTO.builder().build();
		super.entityToDTO(entity, dto);
		
		if(entity.getMember() != null)
			dto.setWriter(entity.getMember().getNickname());
		
		if(dto.getUploadFileNames() != null)
			dto.setUploadFileNames(entity.getImageList().stream().map(BoardImage::getFileName).toList());
		
		return dto;
	}
	
	public Question dtoToEntity(QuestionDTO dto) {
		Question entity = Question.builder().build();
		super.dtoToEntity(dto, entity);
		
		if(dto.getUploadFileNames() != null)
		dto.getUploadFileNames().forEach(entity::addImageString);
		
		return entity;
	}
}
