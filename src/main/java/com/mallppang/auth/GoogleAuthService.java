package com.mallppang.auth;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

@Service
public class GoogleAuthService {
	@Value("${google.client-id}")
	private String googleClientId;
	
	public GoogleIdToken.Payload verifyGoogleIdToken(String idTokenString){
		try {
			GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier
					.Builder(new NetHttpTransport(), new JacksonFactory())
					.setAudience(Collections.singletonList(googleClientId))
					.build();
			GoogleIdToken idToken = verifier.verify(idTokenString);
			if(idToken != null)
				return idToken.getPayload();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
