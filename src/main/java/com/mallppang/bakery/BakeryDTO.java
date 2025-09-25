package com.mallppang.bakery;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class BakeryDTO {
	// 등록 번호
	private Long id;
	
	// 가게 이름
	private String name;
	
	// 주소
	private String loadAddress;
	private String townAddress;
	
	// open time and close time
	private Date openDate;
	private Date closeDate;
	
	// 주차
	private boolean parking;
	
	// 물품 목록
	@Builder.Default
	private List<String> products = new ArrayList<>();
}
