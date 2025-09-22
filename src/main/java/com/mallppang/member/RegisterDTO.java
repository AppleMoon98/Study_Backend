package com.mallppang.member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RegisterDTO {
	private String email;
	private String password;
	private String nickname;
	private String telNum;
}
