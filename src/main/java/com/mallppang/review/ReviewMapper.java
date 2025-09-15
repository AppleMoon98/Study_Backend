package com.mallppang.review;

import org.springframework.stereotype.Component;

import com.mallppang.base.BoardImage;
import com.mallppang.base.BoardMapper;

@Component
public class ReviewMapper extends BoardMapper {

	public ReviewDTO entityToDTO(ReviewBoard reviewBoard) {
		ReviewDTO reviewDTO = ReviewDTO.builder().build();
		super.entityToDTO(reviewBoard, reviewDTO);

		if (reviewBoard.getImageList() != null)
			reviewDTO.setUploadFileNames(reviewBoard.getImageList().stream().map(BoardImage::getFileName).toList());
		
		if(reviewBoard.getMember() != null)
		reviewDTO.setWriter(reviewBoard.getMember().getNickname());
		
		return reviewDTO;
	}

	public ReviewBoard dtoToEntity(ReviewDTO reviewDTO) {
		ReviewBoard entity = ReviewBoard.builder().build();
		super.dtoToEntity(reviewDTO, entity);
		System.err.println(reviewDTO.getUploadFileNames().toString());

		if (reviewDTO.getUploadFileNames() != null)
			reviewDTO.getUploadFileNames().forEach(entity::addImageString);

		return entity;
	}
}
