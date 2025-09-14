package com.mallppang.member;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table
@ToString(exclude = "memberRoleList")
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long uid;
	
	@Column(unique = true)
	private String email;
	
	@Column(unique = true)
	private String telNum;
	
	@Column(unique = true)
	private String nickname;
	private String password;
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
}
