package com.mallppang.auth;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.mallppang.auth.dto.KakaoTokenResponse;
import com.mallppang.auth.dto.KakaoUserResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class KakaoApi {
	private final RestTemplate rest = new RestTemplate();

	public KakaoTokenResponse changeToken(String code, String clientId, String redirectUri, String clientSecret) {
		String url = "https://kauth.kakao.com/oauth/token";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
		form.add("grant_type", "authorization_code");
		form.add("client_id", clientId);
		form.add("redirect_uri", redirectUri);
		form.add("code", code);

		if (clientSecret != null && !clientSecret.isBlank())
			form.add("client_secret", clientSecret);

		ResponseEntity<KakaoTokenResponse> res = rest.postForEntity(url, new HttpEntity<>(form, headers),
				KakaoTokenResponse.class);
		return res.getBody();
	}

	public KakaoUserResponse mem(String accessToken) {
		String url = "https://kapi.kakao.com/v2/user/me";
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(accessToken);
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		ResponseEntity<KakaoUserResponse> res = rest.postForEntity(url,
				new HttpEntity<>(new LinkedMultiValueMap<>(), headers), KakaoUserResponse.class);
		return res.getBody();
	}
}
