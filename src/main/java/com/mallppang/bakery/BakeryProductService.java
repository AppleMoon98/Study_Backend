package com.mallppang.bakery;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class BakeryProductService {
	private final BakeryProductRepository productRepository;
	private final BakeryProductMapper mapper;
	
	public Long register(BakeryProductDTO dto) {
		BakeryProduct product = mapper.dtoToEntity(dto);
		return productRepository.save(product).getId();
	}
}
