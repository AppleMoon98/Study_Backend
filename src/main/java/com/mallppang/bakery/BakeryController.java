package com.mallppang.bakery;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BakeryController {
	private final BakeryService bakeryService;
	private final BakeryMapping mapping;
	
	@GetMapping("api/bakeries")
	public List<BakeryDTO> getBakeries(){
				return bakeryService.getAllBakeries().stream().map(mapping::entityToDTO).collect(Collectors.toList());
	}
}
