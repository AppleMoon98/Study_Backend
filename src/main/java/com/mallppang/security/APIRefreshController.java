package com.mallppang.security;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mallppang.util.JWTUtil;
// import io.jsonwebtoken.ExpiredJwtException; // 사용중인 JWT 라이브러리에 맞춰 import

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
public class APIRefreshController {

    @GetMapping("/member/refresh")
    public Map<String, String> refresh(
            @RequestHeader(value = "Authorization") String authHeader,
            @RequestParam("refreshToken") String refreshToken) {

        if (refreshToken == null || refreshToken.isBlank()) {
            throw new RuntimeException("NULL_REFRESH"); // ← 프로젝트의 Custom 예외로 교체 권장
        }

        // 1) access token 추출(없으면 null)
        String accessToken = null;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            accessToken = authHeader.substring(7);
        }

        // 2) access 만료 여부 판단
        boolean accessExpired = true;
        if (accessToken != null) {
            try {
                JWTUtil.validateToken(accessToken);
                accessExpired = false;
            } catch (Exception e) {
                // 만료/위조/기타 오류는 전부 새 access 발급 대상으로 처리
                accessExpired = true;
            }
        }

        // 3) refresh 유효성 검증(여기서 만료/위조면 예외 발생)
        Map<String, Object> claims = JWTUtil.validateToken(refreshToken);
        log.debug("refresh -------------------------------- claims: {}", claims);

        // 4) 토큰 재발급
        String newAccess  = accessExpired ? JWTUtil.generateToken(claims, 10) : accessToken;
        String newRefresh = isRefreshAboutToExpire(claims) ? JWTUtil.generateToken(claims, 60 * 24) : refreshToken;

        return Map.of("accessToken", newAccess, "refreshToken", newRefresh);
    }

    private boolean isRefreshAboutToExpire(Map<String, Object> claims) {
        Object expObj = claims.get("exp");
        long expSec;
        if (expObj instanceof Number) expSec = ((Number) expObj).longValue();
        else expSec = Long.parseLong(String.valueOf(expObj)); // 혹시 문자열인 경우
        long leftMillis = expSec * 1000L - System.currentTimeMillis();
        long leftMin = leftMillis / (1000 * 60);
        return leftMin < 60; // 남은 시간이 60분 미만이면 회전
    }
}
