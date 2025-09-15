package com.mallppang.question;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
import com.mallppang.member.MemberDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/q")
public class QuestionController {
	private final CustomFileUtil fileUtil;
	private final QuestionService questionService;
	
	@PostMapping("/")
	public Map<String, Object> register(QuestionDTO questionDTO, @AuthenticationPrincipal MemberDTO memberDTO) {
		List<MultipartFile> files = questionDTO.getFiles();
		List<String> uploadFileNames = fileUtil.saveFiles(files);
		questionDTO.setUploadFileNames(uploadFileNames);
		
		log.info("Register : " + questionDTO);
		log.info(uploadFileNames);
		
		Long id = questionService.register(questionDTO);
		return Map.of("결과", id);
	}
	
	@GetMapping("/view/{filename}")
	public ResponseEntity<Resource> viewFileGet(@PathVariable("filename") String fileName) {
		return fileUtil.getFile(fileName);
	}
	@GetMapping("/l")
	public PageResponseDTO<QuestionDTO> getList(PageRequestDTO pageRequestDTO) {
		return questionService.getList(pageRequestDTO);
	}
	
	@GetMapping("/{id}")
	public QuestionDTO read(@PathVariable("id") Long id) {
		return questionService.get(id);
	}
	
	@PutMapping("/{id}")
	public Map<String, String> modify(@PathVariable("id") Long id, QuestionDTO questionDTO) {
		questionDTO.setId(id);
		
		QuestionDTO oldQuestionDTO = questionService.get(id);
		List<String> oldFileNames = oldQuestionDTO.getUploadFileNames();
		List<MultipartFile> files = questionDTO.getFiles();
		List<String> currentUploadFileNames = fileUtil.saveFiles(files);
		List<String> uploadFileNames = questionDTO.getUploadFileNames();
		
		if (currentUploadFileNames != null && currentUploadFileNames.size() > 0)
			uploadFileNames.addAll(currentUploadFileNames);
		questionService.modify(questionDTO);
		
		if (oldFileNames != null && oldFileNames.size() > 0) {
			List<String> removeFiles = oldFileNames
						 .stream()
						 .filter(fileName -> uploadFileNames.indexOf(fileName) == -1)
						 .collect(Collectors.toList());
			fileUtil.deleteFiles(removeFiles);
		}
	
		return Map.of("수정", "성공");
	}
	
	@DeleteMapping("/{id}")
	public Map<String, String> remove(@PathVariable("id") Long id) {
		List<String> oldFileNames = questionService.get(id).getUploadFileNames();
		questionService.delete(id);
		fileUtil.deleteFiles(oldFileNames);
		return Map.of("삭제", "성공");
	}
}
