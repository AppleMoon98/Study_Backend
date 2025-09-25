package com.mallppang.freeboard;

import org.springframework.stereotype.Component;

import com.mallppang.base.BoardImage;
import com.mallppang.base.BoardMapper;

@Component
public class FreeMapper extends BoardMapper {
	
	public FreeDTO entityToDTO(FreeBoard entity) {
		FreeDTO dto = FreeDTO.builder().build();
		super.entityToDTO(entity, dto);
		
		if(entity.getMember() != null) {
			dto.setWriter(entity.getMember().getNickname());
			dto.setEmail(entity.getMember().getEmail());
		}
		
		if(entity.getImageList() != null)
		dto.setUploadFileNames(entity.getImageList().stream().map(BoardImage::getFileName).toList());
		
		return dto;
	}

	public FreeBoard dtoToEntity(FreeDTO dto) {
		FreeBoard entity = FreeBoard.builder().build();
		super.dtoToEntity(dto, entity);

		if (dto.getUploadFileNames() != null)
			dto.getUploadFileNames().forEach(entity::addImageString);

		return entity;
	}
}
