package com.mallppang.freeboard;

import org.springframework.stereotype.Component;

import com.mallppang.base.BoardImage;
import com.mallppang.base.BoardMapper;

@Component
public class FreeMapper extends BoardMapper {
	
	public FreeDTO entityToDTO(FreeBoard entity) {
		FreeDTO dto = FreeDTO.builder().build();
		super.entityToDTO(entity, dto);
		
		if(entity.getImageList() != null)
			dto.setUploadFileNames(entity.getImageList().stream().map(BoardImage::getFileName).toList());
		
		// 작성자 강제 설정용
		if(entity.getMember() != null)
			dto.setWriter(entity.getMember().getNickname());
		
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
