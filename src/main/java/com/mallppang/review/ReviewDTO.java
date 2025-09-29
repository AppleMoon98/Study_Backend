package com.mallppang.review;

import com.mallppang.base.BaseDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ReviewDTO extends BaseDTO{
	private String writer;
	private String email;
	
	private Long bakeryId;
	
}
