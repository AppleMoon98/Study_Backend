package com.example.mall.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "memberRoleList")	// 순환 참조 오류 방지
public class Member {
	@Id
	private String email;
	private String pw;
	private String nickname;
	private boolean social;
	
	@ElementCollection(fetch = FetchType.LAZY)
	@Builder.Default
	private List<MemberRole> memberRoleList = new ArrayList<>();
	
	public void addRole(MemberRole memberRole) {
		memberRoleList.add(memberRole);
	}
	
	public void clearRole() {
		memberRoleList.clear();
	}
	
	// 보안을 위해서 Setter를 지우고 메서드를 새로 만들기도 함
	// ex) changePw( String pw ) { this.pw = pw; }
}
