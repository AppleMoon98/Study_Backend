package com.example.mall.service;

//GoogleAuthService.java
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class GoogleAuthService {

	@Value("${google.client-id}")
	private String googleClientId;

	public GoogleIdToken.Payload verifyGoogleIdToken(String idTokenString) {
		try {
			GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier
					.Builder(new NetHttpTransport(), new JacksonFactory())
                    .setAudience(Collections.singletonList(googleClientId))
                    .build();

			GoogleIdToken idToken = verifier.verify(idTokenString);
			if (idToken != null) {
				return idToken.getPayload();
			}
		} catch (Exception e) {
			// 예외 처리
			e.printStackTrace();
		}
		return null;
	}
}