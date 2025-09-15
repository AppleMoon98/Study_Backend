package com.mallppang.member;

import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService{
//	private final MemberMapper mapper;
//	private final MemberRepository repository;
	

	@Override
	public Long register(MemberDTO dto) {
		
		return null;
	}

	@Override
	public void delete(Long uid) {
		
		
	}

	@Override
	public MemberDTO get(String email, String password) {
//		password = 
//		Member entity = repository.findByEmailAndPassword(email, password);
//		return mapper.entityToDTO(entity);
		return null;
	}

}
