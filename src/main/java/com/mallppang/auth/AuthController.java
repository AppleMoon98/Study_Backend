package com.mallppang.auth;

import java.net.URLEncoder;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletWebRequest;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.gson.Gson;
import com.mallppang.member.Member;
import com.mallppang.member.MemberDTO;
import com.mallppang.member.MemberMapper;
import com.mallppang.member.MemberRepository;
import com.mallppang.member.MemberRole;
import com.mallppang.util.JWTUtil;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/member/auth")
@RequiredArgsConstructor
public class AuthController {

	private final GoogleAuthService googleAuthService;
	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;
	private final MemberMapper memberMapper;

	/**
	 * 구글 로그인 설정
	 * 
	 * @param body
	 * @param response
	 * @return
	 */
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
					.nickname(name != null ? name : email).social(true).telNum(null).build();
			member.addRole(MemberRole.MEMBER);
			member = memberRepository.save(member);
		} else if (!member.isSocial()) {
			// 기존에 같은 이메일의 주소가 있을 경우
			// social 자동 연결
			member.setSocial(true);
			memberRepository.save(member);
		}

		// JWT 발급
		MemberDTO dto = memberMapper.entityToDTO(member);
		Map<String, Object> claims = dto.getClaims();

		// JWTCheckFilter가 읽을 수 있도록 쿠키 member에 저장
		String accessToken = JWTUtil.generateToken(claims, 10);
		String refreshToken = JWTUtil.generateToken(claims, 60 * 24);
		claims.put("accessToken", accessToken);
		claims.put("refreshToken", refreshToken);

		String memberJson = new Gson().toJson(claims);

		Cookie memberCookie = new Cookie("member", URLEncoder.encode(memberJson));
		memberCookie.setHttpOnly(false);
		memberCookie.setSecure(true);
		memberCookie.setPath("/");
		memberCookie.setMaxAge(60 * 60 * 24);
		response.addCookie(memberCookie);

		System.err.println(memberCookie.getValue());
		return ResponseEntity.ok(Map.of("email", dto.getEmail(), "nickname", dto.getNickname(), "roleNames",
				dto.getRoleNames(), "accessToken", accessToken, "refreshToken", refreshToken));
	}

	@PostMapping("/kakao")
	private ResponseEntity<?> testget(@RequestBody KakaoDTO dto, HttpServletResponse response) {
		System.err.println(dto);
		String email = dto.getEmail();
		String name = dto.getNickname();
		if (email == null || email.isBlank())
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "EMAIL_REQUIRED"));

		Member member = memberRepository.getWithRoles(email);
		if (member == null) {
			member = Member.builder().email(email).password(passwordEncoder.encode("SOCIAL_LOGIN"))
					.nickname(name != null ? name : email).social(true).telNum(null).build();
			member.addRole(MemberRole.MEMBER);
			member = memberRepository.save(member);
		} else if (!member.isSocial()) {
			// 기존에 같은 이메일의 주소가 있을 경우
			// social 자동 연결
			member.setSocial(true);
			memberRepository.save(member);
		}

		// JWT 발급
		MemberDTO memberDTO = memberMapper.entityToDTO(member);
		Map<String, Object> claims = memberDTO.getClaims();

		// JWTCheckFilter가 읽을 수 있도록 쿠키 member에 저장
		String accessToken = JWTUtil.generateToken(claims, 10);
		String refreshToken = JWTUtil.generateToken(claims, 60 * 24);
		claims.put("accessToken", accessToken);
		claims.put("refreshToken", refreshToken);

		String memberJson = new Gson().toJson(claims);

		Cookie memberCookie = new Cookie("member", URLEncoder.encode(memberJson));
		memberCookie.setHttpOnly(false);
		memberCookie.setSecure(true);
		memberCookie.setPath("/");
		memberCookie.setMaxAge(60 * 60 * 24);
		response.addCookie(memberCookie);

		System.err.println(memberCookie.getValue());
		return ResponseEntity.ok(Map.of("email", memberDTO.getEmail(), "nickname", memberDTO.getNickname(), "roleNames",
				memberDTO.getRoleNames(), "accessToken", accessToken, "refreshToken", refreshToken));
	}
}
