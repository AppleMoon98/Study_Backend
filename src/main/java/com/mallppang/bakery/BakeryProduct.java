package com.mallppang.bakery;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BakeryProduct {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	// 이름
	private String name;
	
	// 상품 설명
	private String content;
	
	// 썸네일
	private String fileName;
	
	// 가격
	private String price;
	
	// 쿼리문 참조
	@ManyToOne(fetch = FetchType.LAZY)
	private Bakery bakery;
}
