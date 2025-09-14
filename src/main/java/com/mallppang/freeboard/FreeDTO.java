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
}
