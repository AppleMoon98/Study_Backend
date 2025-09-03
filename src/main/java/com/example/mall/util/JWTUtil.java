package com.example.mall.util;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Map;

import javax.crypto.SecretKey;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.InvalidClaimException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class JWTUtil {
	private static String key = "1234567890123456789012345678901234567890";

	public static String generateToken(Map<String, Object> valueMap, int min) {
		SecretKey key = null;
		try {
			key = Keys.hmacShaKeyFor(JWTUtil.key.getBytes("UTF-8"));
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		
		String jwtStr = Jwts.builder()
				.setHeader(Map.of("typ", "JWT"))
				.setClaims(valueMap)
				.setIssuedAt(Date.from(ZonedDateTime.now().toInstant()))	// 토큰 발급 시간
				.setExpiration(Date.from(ZonedDateTime.now().plusMinutes(min).toInstant()))	// 토큰 만료 시간
				.signWith(key)	// 디지털 서명
				.compact();
		
		return jwtStr;
	}
	
	public static Map<String, Object> validateTokent(String token){
		Map<String, Object> claim = null;
		
		try {
			SecretKey key = Keys.hmacShaKeyFor(JWTUtil.key.getBytes("UTF-8"));
			
			claim = Jwts.parserBuilder()
					.setSigningKey(key)
					.build()
					.parseClaimsJws(token)
					.getBody();
		} catch (MalformedJwtException e) {	// 토큰 형식
			throw new CustomJWTExcaption("MalFormed");
		} catch (ExpiredJwtException e) {	// 유효시간 만료
			throw new CustomJWTExcaption("Expired");
		} catch (InvalidClaimException e) {	// 클레임 비유효
			throw new CustomJWTExcaption("Invalid");
		} catch (JwtException e) {	
			throw new CustomJWTExcaption("JWTError");
		} catch (Exception e) {
			throw new CustomJWTExcaption("Error");
		}
		
		return claim;
	}
}
