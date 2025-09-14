package com.mallppang.base;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardImage {
	private String fileName;
	private int ord;
	
	public void setOrd(int ord) {
		this.ord = ord;
	}
}
