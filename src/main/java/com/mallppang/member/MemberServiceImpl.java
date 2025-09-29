package com.mallppang.member;

import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService{
	private final MemberMapper mapper;
	private final MemberRepository repository;
	private final PasswordEncoder passwordEncoder;
	

	@Override
	public void register(RegisterDTO dto, boolean seller) {
		Member member = Member.builder().email(dto.getEmail()).nickname(dto.getNickname()).telNum(dto.getTelNum()).joinedAt(LocalDateTime.now()).build();
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

	@Override
	public void modifyNickname(MemberDTO dto, String data) {
		if(dto == null || data == null)
			return;
		
		Member member = repository.findByEmail(dto.getEmail()).orElseThrow();
		member.setNickname(data);
		repository.save(member);
	}

	@Override
	public void modifyPassword(Member entity, String data) {
		if(entity == null || data == null)
			return;
		
		entity.setPassword(passwordEncoder.encode(data));
		repository.save(entity);
	}
}
