package com.mallppang.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.mallppang.member.Member;
import com.mallppang.member.MemberRepository;
import com.mallppang.member.MemberRole;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class MemberRepositoryTest {

	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Test
	public void testInsertMember() {
		// 아이디 복사버그 드간다 와쟈쟈쟛
//		for(int i = 0; i < 10; i++) {
			Member member = Member.builder().email("e" + 1)
					.password(passwordEncoder.encode("123"))
					.nickname("member" + 1)
					.build();
			
			member.addRole(MemberRole.MEMBER);
//			if(i >= 5) 
				member.addRole(MemberRole.SELLER);
//			if(i >= 8) 
				member.addRole(MemberRole.ADMIN);
			
			memberRepository.save(member);
//		}
		
		
		
	}
}
