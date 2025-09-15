package com.mallppang.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mallppang.auth.dto.KakaoTokenResponse;
import com.mallppang.auth.dto.KakaoUserResponse;
import com.mallppang.member.Member;
import com.mallppang.member.MemberRepository;
import com.mallppang.member.MemberRole;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class KakaoAuthService {
	private final KakaoApi kakaoApi;
	private final MemberRepository memberRepository;

	@Value("${kakao.client-id}")
	private String clientId;

	@Value("${kakao.redirect-uri}")
	private String redirectUri;

	@Value("${kakao.client-secret:}")
	private String clientSecret;

	@Transactional
	public Member upsertByAuthCode(String code) {
		KakaoTokenResponse token = kakaoApi.changeToken(code, clientId, redirectUri, clientSecret);
		KakaoUserResponse me = kakaoApi.mem(token.getAccess_token());

		String email = me.getKakao_account() != null ? me.getKakao_account().getEmail() : null;
		if (email == null || email.isBlank())
			throw new IllegalStateException("카카오에서 이메일을 받지 못했습니다. 콘솔에서 account_email 항목을 확인해주세요.");

		String nickname = me.getKakao_account() != null ? me.getKakao_account().getProfile().getNickname() : "카카오사용자";
		Member member = memberRepository.findByEmail(email)
				.orElseGet(() -> Member.builder().email(email).nickname(nickname).social(true).build());
		
		if(member.getMemberRoleList().isEmpty())
			member.addRole(MemberRole.MEMBER);
		
		member.setNickname(nickname);
		
		return memberRepository.save(member);
	}
}
