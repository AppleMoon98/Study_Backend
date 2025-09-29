package com.mallppang.member;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class MemberMapper {
	private MemberRepository repository;

	public MemberDTO entityToDTO(Member e) {
		List<String> roles = e.getMemberRoleList() == null ? List.of()
				: e.getMemberRoleList().stream().map(Enum::name).collect(Collectors.toList());
		MemberDTO memberDTO = new MemberDTO(e.getEmail(), e.getPassword(), e.getNickname(), e.isSocial(), roles, e.getTelNum());
		return memberDTO;

	}

	public Member dtoToEntity(MemberDTO dto) {
		return Member.builder().email(dto.getEmail()).password(dto.getPassword()).nickname(dto.getNickname())
				.social(dto.isSocial()).telNum(dto.getTelNum())
				.memberRoleList((dto.getRoleNames() == null ? List.<String>of() : dto.getRoleNames()).stream()
						.map(MemberRole::valueOf).collect(Collectors.toList()))
				.build();
	}

}
