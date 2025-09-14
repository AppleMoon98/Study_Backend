package com.mallppang.auth;

import java.net.URLEncoder;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.gson.Gson;
import com.mallppang.JwtTokenProvider;
import com.mallppang.member.Member;
import com.mallppang.member.MemberDTO;
import com.mallppang.member.MemberRepository;
import com.mallppang.member.MemberRole;
import com.mallppang.util.JWTUtil;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/member/auth")
@AllArgsConstructor
public class AuthController {

	private final JwtTokenProvider jwtTokenProvider;
	private final GoogleAuthService googleAuthService;
	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;

	@PostMapping("/google")
	public ResponseEntity<?> googleLogin(@RequestBody Map<String, String> body, HttpServletResponse response) {
		// 값 받기
		String idToken = body.getOrDefault("credential", body.get("idToken"));
		if (idToken == null || idToken.isBlank())
			return ResponseEntity.badRequest().body(Map.of("error", "MISSING_ID_TOKEN"));

		GoogleIdToken.Payload payload = googleAuthService.verifyGoogleIdToken(idToken);
		if (payload == null)
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "INVALID_ID_TOKKEN"));

		String email = payload.getEmail();
		String name = (String) payload.get("name");
		if (email == null || email.isBlank())
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "EMAIL_REQUIRED"));

		// 조회 및 생성
		Member member = memberRepository.getWithRoles(email);
		if (member == null) {
			member = Member.builder().email(email).password(passwordEncoder.encode("SOCIAL_LOGIN"))
					.nickname(name != null ? name : email).social(true).build();
			member.addRole(MemberRole.MEMBER);
			member = memberRepository.save(member);
		} else if (!member.isSocial()) {
			// 기존에 같은 이메일의 주소가 있을 경우
			// social 자동 연결
			member.setSocial(true);
			memberRepository.save(member);
		}

		// JWT 발급
		MemberDTO dto = new MemberDTO(member.getEmail(), member.getPassword(), member.getNickname(), member.isSocial(),
				member.getMemberRoleList().stream().map(Enum::name).toList());
		Map<String, Object> claims = dto.getClaims();

		// JWTCHeckFilter가 읽을 수 있도록 쿠키 member에 저장
		String accessToken = JWTUtil.generateToken(claims, 10);
		String refreshToken = JWTUtil.generateToken(claims, 60 * 24);
		String memberJson = new Gson().toJson(Map.of("accessToken", accessToken, "refreshToken", refreshToken));

		Cookie memberCookie = new Cookie("member", URLEncoder.encode(memberJson));
		memberCookie.setHttpOnly(false);
		memberCookie.setSecure(true);
		memberCookie.setPath("/");
		memberCookie.setMaxAge(60 * 60 * 24);
		response.addCookie(memberCookie);
		
		return ResponseEntity.ok(Map.of(
				"email", dto.getEmail(),
				"nickname", dto.getNickname(),
				"roleNames", dto.getRoleNames(),
				"accessToken", accessToken,
				"refreshToken", refreshToken
				));
	}
}
