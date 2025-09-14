package com.mallppang.freeboard;

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
@RequestMapping("/fc")
public class FreeCommentController {
	private final FreeCommentService commentService;
	
	// 댓글 등록
	@PostMapping("/{boardId}")
	public Map<String, Long> register(@RequestBody FreeCommentDTO freeDTO, @PathVariable("boardId") Long boardId, @AuthenticationPrincipal MemberDTO memberDTO){
		Long logNum = commentService.register(freeDTO, boardId);
		return Map.of("결과", logNum);
	}
	
	// 댓글 수정
	@PutMapping("/{id}")
	public Map<String, String> modify(@PathVariable("id") Long id, FreeCommentDTO freeDTO){
		freeDTO.setId(id);
		commentService.modify(freeDTO);
		return Map.of("수정", "성공");
	}
	
	// 댓글 삭제
	@DeleteMapping("/{id}")
	public Map <String, String> delete(@PathVariable("id") Long id){
		commentService.delete(id);
		return Map.of("삭제", "성공");
	}
	
	// 댓글 리스트 받기
	@GetMapping("/{boardId}")
	public List<FreeCommentDTO> getList(@PathVariable("boardId") Long boardId){
		return commentService.getList(boardId);
	}
}
