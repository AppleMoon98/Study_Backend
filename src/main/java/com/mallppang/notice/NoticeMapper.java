package com.mallppang.notice;

import org.springframework.stereotype.Component;

import com.mallppang.base.BoardImage;
import com.mallppang.base.BoardMapper;


@Component
public class NoticeMapper extends BoardMapper {
	public NoticeDTO entityToDTO(NoticeBoard entity) {
		NoticeDTO dto = NoticeDTO.builder().build();
		super.entityToDTO(entity, dto);

		// Notice 전용 필드 - 필요할 경우 사용
//		dto.setStartDate(entity.getStartDate());
//		dto.setEndDate(entity.getEndDate());

		if (entity.getImageList() != null) 
			dto.setUploadFileNames(entity.getImageList().stream().map(BoardImage::getFileName).toList());
		
		return dto;
	}
	
	public NoticeBoard dtoToEntity(NoticeDTO dto) {
		NoticeBoard entity = NoticeBoard.builder().build();
		super.dtoToEntity(dto, entity);
		
		// Notice 전용 필드 - 필요한 경우 사용
//		entity.setStartDate(dto.getStartDate());
//		entity.setEndDate(dto.getEndDate());
		
		if(dto.getUploadFileNames() != null)
			dto.getUploadFileNames().forEach(entity::addImageString);
		
		return entity;
	}
}
