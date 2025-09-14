package com.mallppang.member;

public interface MemberService {
	Long register(MemberDTO dto);	// 회원 등록
	MemberDTO get(String email, String password);	// 회원 조회
	void delete(Long uid); // 계정 삭제
}
