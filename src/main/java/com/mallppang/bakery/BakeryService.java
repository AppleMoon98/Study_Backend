package com.mallppang.bakery;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class BakeryService {
	private final BakeryRepository bakeryRepository;
	private final BakeryMapping mapper;
	
	// 운영자용 등록, 일단 백에서만 사용할 목적으로 생성
	public Long register(BakeryDTO dto) {
		Bakery bakery = mapper.dtoToEntity(dto);
		return bakeryRepository.save(bakery).getId();
	}
	
	public List<Bakery> getAllBakeries(){
		return bakeryRepository.findAll();
	}
}
