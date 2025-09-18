package com.mallppang.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class NaverAuthService {
	
	@Value("${naver.client-id}")
	private String naverClientId;

}
