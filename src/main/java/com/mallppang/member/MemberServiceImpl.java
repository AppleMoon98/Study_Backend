package com.mallppang.member;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
	private final MemberMapper mapper;
	private final MemberRepository repository;
	private final PasswordEncoder passwordEncoder;
	

	@Override
	public void register(RegisterDTO dto, boolean seller) {
		Member member = Member.builder().email(dto.getEmail()).nickname(dto.getNickname()).telNum(dto.getTelNum()).build();
		member.addRole(MemberRole.MEMBER);
		if(seller)
			member.addRole(MemberRole.SELLER);
		
		member.setPassword(passwordEncoder.encode(dto.getPassword()));
		try {
			repository.save(member);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	@Override
	public void delete(Member entity) {
		repository.delete(entity);
		// 일단 간단하게 바로 강제로 삭제함.
	}

	@Override
	public MemberDTO get(String email, String password) {
		Member member = repository.findByEmailAndPassword(email, password);
		
		// 멤버가 없을 때, 리턴값이 없어서 나중에 정해줘야함.
		if(member == null)
			return null;
		
		MemberDTO dto = mapper.entityToDTO(member);
		return dto;
	}
}
