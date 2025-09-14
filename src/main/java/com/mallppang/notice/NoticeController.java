 package com.mallppang.notice;

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
@RequestMapping("/n")
public class NoticeController {
	private final CustomFileUtil fileUtil;
	private final NoticeService noticeService;

	@PostMapping("/")
	public Map<String, Long> register(NoticeDTO noticeDTO) {
		List<MultipartFile> files = noticeDTO.getFiles();
		List<String> uploadFileNames = fileUtil.saveFiles(files);
		noticeDTO.setUploadFileNames(uploadFileNames);

		log.info("Register : " + noticeDTO);
		log.info(uploadFileNames);

		Long id = noticeService.register(noticeDTO);
		return Map.of("결과", id);
	}

	@GetMapping("/view/{filename}")
	public ResponseEntity<Resource> viewFileGet(@PathVariable("filename") String fileName) {
		return fileUtil.getFile(fileName);
	}

	@GetMapping("/l")
	public PageResponseDTO<NoticeDTO> getList(PageRequestDTO pageRequestDTO) {
		return noticeService.getList(pageRequestDTO);
	}

	@GetMapping("/{id}")
	public NoticeDTO read(@PathVariable("id") Long id) {
		return noticeService.get(id);
	}

	@PutMapping("/{id}")
	public Map<String, String> modify(@PathVariable("id") Long id, NoticeDTO noticeDTO) {
		noticeDTO.setId(id);
		
		NoticeDTO oldNoticeDTO = noticeService.get(id);
		List<String> oldFileNames = oldNoticeDTO.getUploadFileNames();
		List<MultipartFile> files = noticeDTO.getFiles();
		List<String> currentUploadFileNames = fileUtil.saveFiles(files);
		List<String> uploadFileNames = noticeDTO.getUploadFileNames();

		if (currentUploadFileNames != null && currentUploadFileNames.size() > 0)
			uploadFileNames.addAll(currentUploadFileNames);
		noticeService.modify(noticeDTO);

		if (oldFileNames != null && oldFileNames.size() > 0) {
			List<String> removeFiles = oldFileNames.stream().filter(fileName -> uploadFileNames.indexOf(fileName) == -1)
					.collect(Collectors.toList());
			fileUtil.deleteFiles(removeFiles);
		}

		return Map.of("수정", "성공");
	}

	@DeleteMapping("/{id}")
	public Map<String, String> remove(@PathVariable("id") Long id) {
		List<String> oldFileNames = noticeService.get(id).getUploadFileNames();
		noticeService.delete(id);
		fileUtil.deleteFiles(oldFileNames);
		return Map.of("삭제", "성공");
	}
}
