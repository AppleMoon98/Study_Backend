package com.mallppang.bakery;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class BakeryProductDTO {
	private Long id;
	private String name;
	private String content;
	private String fileName;
	private String price;
	private Long bakeryId;
}
