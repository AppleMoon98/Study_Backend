package com.mallppang.bakery;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bakeries")
public class BakeryController {
	private final BakeryService bakeryService;
	private final BakeryProductService productService;
	
	@GetMapping("/")
	public List<BakeryDTO> getBakeries(){
		return bakeryService.getBakeries();
	}
	
	@GetMapping("/product/{bakeryId}")
	public List<BakeryProductDTO> getBakeryProducts(@PathVariable("bakeryId") Long bakeryId){
		return productService.getList(bakeryId);
	}
}
