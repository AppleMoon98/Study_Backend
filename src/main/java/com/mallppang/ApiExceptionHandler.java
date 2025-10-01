package com.mallppang;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 비밀번호 변경 관련 오류 관리
 * handleBadCredential : 현재 비밀번호 칸에 적은 비밀번호가 실제와 다를 경우
 * handleUserNotFound : 회원을 찾았는데 없는 경우 / 물론 이런 경우는 잘 발생하지 않겠지만.
 * handleIllegalArgument : 나머지 에러, 근데 완전 나머지가 아니라 내가 설정한.
 */
@RestControllerAdvice
public class ApiExceptionHandler {
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<Map<String, String>> handleBadCredentials(BadCredentialsException ex) {
		return ResponseEntity.badRequest().body(Map.of("message", "현재 비밀번호가 일치하지 않습니다."));
	}

	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<Map<String, String>> handleUserNotFound(UsernameNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "회원이 존재하지 않습니다."));
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Map<String, String>> handleIllegalArgument(IllegalArgumentException ex) {
		return ResponseEntity.badRequest().body(Map.of("message", ex.getMessage()));
	}
}
