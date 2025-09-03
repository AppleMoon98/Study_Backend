package com.example.mall.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.mall.dto.PageRequestDTO;
import com.example.mall.dto.PageResponseDTO;
import com.example.mall.dto.ProductDTO;
import com.example.mall.service.ProductService;
import com.example.mall.util.CustomFileUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/api/products")
public class ProductController {
	private final CustomFileUtil fileUtil;
	private final ProductService productService;
	
	@PostMapping("/")
	public Map<String, Long> register(ProductDTO productDTO){
		
		List<MultipartFile> files = productDTO.getFiles();
		List<String> uploadFileNames = fileUtil.saveFiles(files);
		
		productDTO.setUploadFileNames(uploadFileNames);
		
		log.info("register : " + productDTO);
		log.info(uploadFileNames);
		
		Long pno = productService.register(productDTO);
		return Map.of("결과",pno);
	}

	@GetMapping("/view/{filename}")
	public ResponseEntity<Resource> viewFileGet(@PathVariable("filename") String fileName){
		return fileUtil.getFile(fileName);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")	// 해당 권한만 접근 가능
	@GetMapping("/list")
	public PageResponseDTO<ProductDTO> list(PageRequestDTO pageRequestDTO){
		log.info("list------------------------------------------------------------------" + pageRequestDTO);
		return productService.getList(pageRequestDTO);
	}
	
	@GetMapping("/{pno}")
	public ProductDTO read(@PathVariable("pno") Long pno) {
		return productService.get(pno);
	}
	
	@PutMapping("/{pno}")
	public Map<String, String> modify(@PathVariable("pno") Long pno, ProductDTO productDTO){
		productDTO.setPno(pno);
		ProductDTO oldProductDTO = productService.get(pno);
		List<String> oldFileNames = oldProductDTO.getUploadFileNames();
		List<MultipartFile> files = productDTO.getFiles();
		List<String> currentUploadFileNames = fileUtil.saveFiles(files);
		List<String> uploadFileNames = productDTO.getUploadFileNames();
		
		if(currentUploadFileNames != null && currentUploadFileNames.size() > 0)
			uploadFileNames.addAll(currentUploadFileNames);
		productService.modify(productDTO);
		
		if(oldFileNames != null && oldFileNames.size() > 0) {
			List<String> removeFiles = oldFileNames.stream()
					.filter(fileName -> uploadFileNames.indexOf(fileName) == -1).collect(Collectors.toList());
			fileUtil.deleteFiles(removeFiles);
		}
		
		return Map.of("결과","성공");
	}
	
	@DeleteMapping("/{pno}")
	public Map<String, String> remove(@PathVariable("pno") Long pno){
		List<String> oldFileNames = productService.get(pno).getUploadFileNames();
		productService.remove(pno);
		fileUtil.deleteFiles(oldFileNames);
		return Map.of("삭제","성공");
	}
}
