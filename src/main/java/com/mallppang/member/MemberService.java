package com.mallppang.member;

public interface MemberService {
	void register(RegisterDTO dto, boolean seller);	// 회원 등록(임시)
	MemberDTO get(String email, String password);	// 회원 조회(임시)
	void delete(Member entity); // 계정 삭제(임시)
}
