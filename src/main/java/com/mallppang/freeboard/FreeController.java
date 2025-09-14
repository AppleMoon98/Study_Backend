package com.mallppang.freeboard;

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
@RequestMapping("/f")
public class FreeController {
	private final CustomFileUtil fileUtil;
	private final FreeService freeService;

	@PostMapping("/")
	public Map<String, Long> register(FreeDTO freeDTO, @AuthenticationPrincipal MemberDTO memberDTO) {
		List<MultipartFile> files = freeDTO.getFiles();
		List<String> uploadFileNames = fileUtil.saveFiles(files);
		freeDTO.setUploadFileNames(uploadFileNames);

		log.info("Register : " + freeDTO);
		log.info(uploadFileNames);

		Long id = freeService.register(freeDTO);
		return Map.of("결과", id);
	}

	@GetMapping("/view/{filename}")
	public ResponseEntity<Resource> viewFileGet(@PathVariable("filename") String fileName) {
		return fileUtil.getFile(fileName);
	}

	@GetMapping("/l")
	public PageResponseDTO<FreeDTO> getList(PageRequestDTO pageRequestDTO) {
		return freeService.getList(pageRequestDTO);
	}

	@GetMapping("/{id}")
	public FreeDTO read(@PathVariable("id") Long id) {
		return freeService.get(id);
	}

	@PutMapping("/{id}")
	public Map<String, String> modify(@PathVariable("id") Long id, FreeDTO freeDTO) {
		freeDTO.setId(id);
		
		FreeDTO oldFreeDTO = freeService.get(id);
		List<String> oldFileNames = oldFreeDTO.getUploadFileNames();
		List<MultipartFile> files = freeDTO.getFiles();
		List<String> currentUploadFileNames = fileUtil.saveFiles(files);
		List<String> uploadFileNames = freeDTO.getUploadFileNames();

		if (currentUploadFileNames != null && currentUploadFileNames.size() > 0)
			uploadFileNames.addAll(currentUploadFileNames);
		freeService.modify(freeDTO);

		if (oldFileNames != null && oldFileNames.size() > 0) {
			List<String> removeFiles = oldFileNames.stream().filter(fileName -> uploadFileNames.indexOf(fileName) == -1)
					.collect(Collectors.toList());
			fileUtil.deleteFiles(removeFiles);
		}

		return Map.of("수정", "성공");
	}

	@DeleteMapping("/{id}")
	public Map<String, String> remove(@PathVariable("id") Long id) {
		List<String> oldFileNames = freeService.get(id).getUploadFileNames();
		freeService.delete(id);
		fileUtil.deleteFiles(oldFileNames);
		return Map.of("삭제", "성공");
	}
}
