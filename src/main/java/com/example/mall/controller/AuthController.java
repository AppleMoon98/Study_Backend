package com.example.mall.controller;

//AuthController.java
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mall.dto.TokenRequestDto;
import com.example.mall.service.GoogleAuthService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/member/auth")
@AllArgsConstructor
public class AuthController {

	private final GoogleAuthService googleAuthService;
	// private final MemberService memberService; // 사용자 정보 처리 서비스
	// private final JwtTokenProvider jwtTokenProvider; // 자체 JWT 토큰 생성기

	@PostMapping("/google")
	public ResponseEntity<?> googleLogin(@RequestBody TokenRequestDto requestDto) {
		GoogleIdToken.Payload payload = googleAuthService.verifyGoogleIdToken(requestDto.getToken());
		if (payload != null) {
			String email = payload.getEmail();
			String name = (String) payload.get("name");

			// 1. payload의 이메일로 DB에서 사용자 조회
			// 2. 사용자가 없으면 새로 회원가입 처리 (DB에 저장)
			// 3. 사용자가 있으면 로그인 처리

			// 4. 자체 서비스의 JWT 토큰 생성
			// String appToken = jwtTokenProvider.createToken(email);

			// 5. 생성된 토큰을 클라이언트에 반환
			// return ResponseEntity.ok(new TokenResponseDto(appToken));

			// 임시 응답
			return ResponseEntity.ok("Login Success: " + name + " (" + email + ")");
		}
		return ResponseEntity.status(401).body("Invalid Google ID Token");
	}
}
