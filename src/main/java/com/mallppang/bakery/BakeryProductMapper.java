package com.mallppang.bakery;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class BakeryProductMapper {
	private final BakeryRepository bakeryRepository;
	
	public BakeryProductDTO entityToDTO(BakeryProduct entity) {
		BakeryProductDTO dto = BakeryProductDTO.builder()
																.id(entity.getId())
																.name(entity.getName())
																.content(entity.getContent())
																.fileName(entity.getFileName())
																.bakeryId(entity.getBakery().getId())
																.price(entity.getPrice())
																.build();
		return dto;
	}
	
	public BakeryProduct dtoToEntity(BakeryProductDTO dto) {
		BakeryProduct entity = BakeryProduct.builder()
														.id(dto.getId())
														.name(dto.getName())
														.content(dto.getContent())
														.fileName(dto.getFileName())
														.bakery(bakeryRepository.findById(dto.getBakeryId()).orElseThrow())
														.price(dto.getPrice())
														.build();
		return entity;
	}
}
