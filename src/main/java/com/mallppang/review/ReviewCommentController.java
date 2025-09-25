package com.mallppang.review;

import java.util.List;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mallppang.member.MemberDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/rc")
public class ReviewCommentController {
	private final ReviewCommentService reviewCommentService;
	
	@PostMapping("/{boardId}")//답글 등록
	public Map<String, Long> register(@PathVariable("boardId") Long boardId, @RequestBody ReviewCommentDTO reviewCommentDTO,
				@AuthenticationPrincipal MemberDTO memberDTO) {
	    Long saveId = reviewCommentService.register(reviewCommentDTO, boardId);
	    return Map.of("결과", saveId);
	}
	
	@PutMapping("/{id}")//답글 수정
	public Map<String, String> modify(@PathVariable("id") Long id, @RequestBody ReviewCommentDTO commentDTO){
			commentDTO.setId(id);
			reviewCommentService.modify(commentDTO);
			
		return Map.of("답글 수정", "성공");
	}
	
	@DeleteMapping("/{id}") // 답글 삭제
	public Map<String, String> delete(@PathVariable("id") Long id) {
		reviewCommentService.delete(id);
		
		return Map.of("답글 삭제", "성공");
	}
	
	@GetMapping("/{boardId}") // 답글/댓글 리스트 받기
	public List<ReviewCommentDTO> getList(@PathVariable("boardId") Long boardId){
		return reviewCommentService.getList(boardId);
	}
}

