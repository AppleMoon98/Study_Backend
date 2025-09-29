package com.mallppang.member;

import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {	
	private final MemberService memberService;
	
	@PostMapping("/register")
	private Map<String, String> register(@RequestBody RegisterDTO dto){
		memberService.register(dto, false);
		return Map.of("가입 성공", dto.getEmail());
	}
	
	@PostMapping("/sellerregister")
	private Map<String, String> sellerRegister(@RequestBody RegisterDTO dto){
		memberService.register(dto, true);
		return Map.of("가입 성공", dto.getEmail());
	}
	
	@DeleteMapping("/delete")
	private Map<String, String> delete(@AuthenticationPrincipal Member entity){
		if(entity == null)
			return Map.of("error", "MEMBER_UNDEFINED");
		memberService.delete(entity);
		return Map.of("회원 삭제", "성공 : " + entity.getEmail());
	}
	
	@PutMapping("/nickname")
	private Map<String, String> modifyNickname(@AuthenticationPrincipal MemberDTO memberDTO, @RequestBody String nickname){
		memberService.modifyNickname(memberDTO, nickname);
		return Map.of("변경 성공", nickname);
	}
	
	@PutMapping("/password")
	private Map<String, String> modifyPassword(@AuthenticationPrincipal Member entity, String data){
		memberService.modifyPassword(entity, data);
		return Map.of("변경 성공", "비밀번호");
	}
}
