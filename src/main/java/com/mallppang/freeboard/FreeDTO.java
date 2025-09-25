package com.mallppang.freeboard;

import com.mallppang.base.BaseDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
public class FreeDTO extends BaseDTO {
	private String writer;	// 작성자 출력용
	private String email;	// 수정 및 삭제 권한을 위한
}
