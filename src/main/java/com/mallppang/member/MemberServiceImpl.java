package com.mallppang.member;

import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService{
//	MemberMapper mapper;
//	MemberRepository repository;

	@Override
	public Long register(MemberDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long uid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MemberDTO get(String email, String password) {
//		password = 
//		Member entity = repository.findByEmailAndPassword(email, password);
//		return mapper.entityToDTO(entity);
		return null;
	}

}
