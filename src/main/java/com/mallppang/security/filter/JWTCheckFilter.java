package com.mallppang.security.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.mallppang.member.MemberDTO;
import com.mallppang.util.JWTUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class JWTCheckFilter extends OncePerRequestFilter {
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		if (request.getMethod().equals("OPTIONS"))
			return true;

		String path = request.getRequestURI();
		log.info("check uri" + ".".repeat(20) + path);
		
		// 마이페이지 내용만 따로
		if(path.startsWith("/member/nickname")
				|| path.startsWith("/member/password")
				|| path.startsWith("/api/reservations"))
			return false;

		if (path.startsWith("/member/"))
			return true;
		
		if(path.startsWith("/api/"))
			return true;
		
		return false;
	}
	
	private String resolveBearerToken(HttpServletRequest req) {
	    // Authorization 헤더 우선
	    String auth = req.getHeader("Authorization");
	    if (auth != null && auth.startsWith("Bearer ")) return auth.substring(7);

	    // 없으면 쿠키에서 찾기
	    // (쿠키 이름은 실제 사용 중인 이름으로 바꾸세요: 예 "member", "loginInfo" 등)
	    String raw = getCookieValue(req, "member"); 
	    if (raw == null || raw.isBlank()) return null;

	    // URL 디코딩
	    String decoded = URLDecoder.decode(raw, StandardCharsets.UTF_8);

	    // JSON이면 accessToken 필드 뽑기
	    try {
	        if (decoded.startsWith("{")) {
	            JsonNode node = new ObjectMapper().readTree(decoded);
	            String access = node.path("accessToken").asText(null);
	            if (access != null && !access.isBlank()) return access;
	        } else {
	            // 순수 토큰이 저장된 경우
	            return decoded;
	        }
	    } catch (Exception ignore) {}
	    return null;
	}

	private String getCookieValue(HttpServletRequest req, String name) {
	    Cookie[] cookies = req.getCookies();
	    if (cookies == null) return null;
	    for (Cookie c : cookies) 
	        if (name.equals(c.getName())) return c.getValue();
	    return null;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		log.info("-".repeat(30) + "JWTCheckFilter" + "-".repeat(30));
		String autoHeaderStr = request.getHeader("Authorization");
		String token = resolveBearerToken(request);

		// 헤더가 없을경우
		if (autoHeaderStr == null || !autoHeaderStr.startsWith("Bearer ")) {
			log.info("-".repeat(30) + "Header NPE" + "-".repeat(30));
			filterChain.doFilter(request, response);
			return;
		}
		
		if(token==null) {
			filterChain.doFilter(request, response);
			return;
		}

		try {
			String accessToken = autoHeaderStr.substring(7);
			Map<String, Object> claims = JWTUtil.validateToken(accessToken);
			log.info("JWT claims : " + claims);

			String email = (String) claims.get("email");
			String password = (String) claims.get("password");
			String nickname = (String) claims.get("nickname");
			String telNum = (String) claims.get("telNum");
			Boolean social = (Boolean) claims.get("social");
			List<String> roleNames = (List<String>) claims.get("roleNames");
			MemberDTO memberDTO = new MemberDTO(email, password, nickname, social, roleNames, telNum);

			log.info("-".repeat(30));
			log.info(memberDTO);
			log.info(memberDTO.getAuthorities());

			UsernamePasswordAuthenticationToken authenticationToken = 
					new UsernamePasswordAuthenticationToken(
							memberDTO,
							null,
							memberDTO.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			filterChain.doFilter(request, response);
		} catch (Exception e) {
			log.error("JWT Check ERROR " + "-".repeat(30));
			log.error(e.getMessage());

			Gson gson = new Gson();
			String msg = gson.toJson(Map.of("error", "ERROR_ACCESS_TOKEN"));
			response.setContentType("application/json");

			PrintWriter printWriter = response.getWriter();
			printWriter.println(msg);
			printWriter.close();
		}
	}
}
