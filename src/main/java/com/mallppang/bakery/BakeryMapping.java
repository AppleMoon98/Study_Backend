package com.mallppang.bakery;

import org.springframework.stereotype.Component;

@Component
public class BakeryMapping {
	public BakeryDTO entityToDTO(Bakery entity) {
		BakeryDTO dto = BakeryDTO.builder()
											.id(entity.getId())
											.name(entity.getName())
											.loadAddress(entity.getLoadAddress())
											.townAddress(entity.getTownAddress())
											.openDate(entity.getOpenDate())
											.closeDate(entity.getCloseDate())
											.parking(entity.isParking())
											.build();
		
		return dto;
	}
	
	public Bakery dtoToEntity(BakeryDTO dto) {
		Bakery entity = Bakery.builder()
									.id(dto.getId())
									.name(dto.getName())
									.loadAddress(dto.getLoadAddress())
									.townAddress(dto.getTownAddress())
									.openDate(dto.getOpenDate())
									.closeDate(dto.getCloseDate())
									.parking(dto.isParking())
									.build();
		
		return entity;
	}
}
