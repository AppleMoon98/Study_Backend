package com.mallppang.bakery;

import java.util.ArrayList;
import java.util.List;

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
	
	public List<BakeryProductDTO> getList(Long bakeryId){
		List<BakeryProduct> entityList = productRepository.getList(bakeryId);
		List<BakeryProductDTO> dtoList = new ArrayList<>();
		for(int i = 0; i < entityList.size(); i++)
			dtoList.add(mapper.entityToDTO(entityList.get(i)));
		return dtoList;
	}
}
