package com.mallppang;

import java.nio.charset.StandardCharsets;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

// 클라이언트가 로그인 성공 후 발급받은 JWT 토큰을 Authorization 헤더에 담아 보냄
// JwtTokenProvider.validateAndGetUid(token) 메서드가 호출
// 토큰 서명이 secret 으로 올바르게 검증되면 payload(claims)가 반환
// payload 안에서 "uid" 키에 해당하는 값을 꺼내서 Long 타입으로 반환
// 해당 uid 값을 기반으로 DB 조회를 하거나, 인증/인가 로직에 활용

@Component
public class JwtTokenProvider {
	// JWT 토큰 서명에 필요한 비밀 키
	private final String secret = "J69PO5qyeQGrSZqS4SxduNcrKlcYWOuKGW4H1gTe2u42bIRVKdq3Stl403+Y2rs5j9T/HdaJh6P3XCCm02MVrg==";
	
	// JWT 토큰 검증 및 uid 클레임을 추출 메서드
	public Long validateAndGetUid(String token) {
		var claims = Jwts.parserBuilder()
				.setSigningKey(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)))
				.build()
				.parseClaimsJws(token.replace("Bearer ", ""))
				.getBody();
		return Long.valueOf(claims.get("uid").toString());
	}
}
