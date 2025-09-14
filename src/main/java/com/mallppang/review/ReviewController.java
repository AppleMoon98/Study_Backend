package com.mallppang.review;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mallppang.CustomFileUtil;
import com.mallppang.base.PageRequestDTO;
import com.mallppang.base.PageResponseDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/r")
public class ReviewController {
	private final CustomFileUtil fileUtil;
	private final ReviewService reviewService;
	
	@PostMapping("/")//질문 등록
	public Map<String, Long> register(ReviewDTO reviewDTO){
		log.info("register : " + reviewDTO);
		List<MultipartFile> files = reviewDTO.getFiles();
		List<String> uploadFileNames = fileUtil.saveFiles(files);
		reviewDTO.setUploadFileNames(uploadFileNames);
		
		log.info(uploadFileNames);
		Long id = reviewService.register(reviewDTO);
		return Map.of("결과", id);
	}
	
	@GetMapping("/view/{fileName}")//질문
	public ResponseEntity<Resource> viewFileGet(@PathVariable("fileName") String fileName){
		return fileUtil.getFile(fileName);
	}
	
	@GetMapping("/l")//질문 목록
	public PageResponseDTO<ReviewDTO> list(PageRequestDTO pageRequestDTO){
		return reviewService.getList(pageRequestDTO);
	}
	
	
	@GetMapping("/{id}")//질문 상세보기
	public ReviewDTO read(@PathVariable("id") Long id){
		return reviewService.get(id);
	}
	
	@PutMapping("/{id}")//질문 수정하기
	public Map<String, String> modify(@PathVariable("id") Long id, ReviewDTO reviewDTO){
		reviewDTO.setId(id);
		ReviewDTO oldReviewDTO = reviewService.get(id);
		List<String> oldFileNames = oldReviewDTO.getUploadFileNames();
		
		List<MultipartFile> files = reviewDTO.getFiles();
		
		List<String> currentUploadFileNames = fileUtil.saveFiles(files);
		List<String> uploadFileNames = reviewDTO.getUploadFileNames();
		
		if(currentUploadFileNames != null && currentUploadFileNames.size() > 0)
			uploadFileNames.addAll(currentUploadFileNames);
		reviewService.modify(reviewDTO);
		
		if(oldFileNames != null && oldFileNames.size() > 0){
			List<String> removeFiles = oldFileNames
						.stream()
						.filter(fileName -> uploadFileNames.indexOf(fileName) == -1)
						.collect(Collectors.toList());
			fileUtil.deleteFiles(removeFiles);
		}
		
		return Map.of("수정", "성공");
	}
			
	@DeleteMapping("/{id}")//질문 삭제
	public Map<String, String> delete(@PathVariable("id") Long id){
		List<String> oldFileNames = reviewService.get(id).getUploadFileNames();
		reviewService.delete(id);
		fileUtil.deleteFiles(oldFileNames);
		return Map.of("삭제", "성공");
	}
}
