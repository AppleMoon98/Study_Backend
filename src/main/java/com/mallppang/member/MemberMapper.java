package com.mallppang.member;

public class MemberMapper {
	private MemberRepository repository;
	
	public MemberDTO entityToDTO(Member entity) {
		MemberDTO dto = new MemberDTO(entity.getEmail()
				, entity.getPassword()
				, entity.getNickname()
				, entity.isSocial()
				, null);		// 나중에 수정
		return dto;
	}
	
	public Member dtoToEntity(MemberDTO dto) {
		Member entity = new Member();
		entity = repository.findByEmailAndPassword(dto.getEmail(), dto.getPassword());
		return entity;
	}

}
