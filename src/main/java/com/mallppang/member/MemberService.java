package com.mallppang.member;

public interface MemberService {
	Long register(MemberDTO dto);	// 회원 등록(임시)
	MemberDTO get(String email, String password);	// 회원 조회(임시)
	void delete(Long uid); // 계정 삭제(임시)
}
