package com.mallppang.review;

import com.mallppang.base.BaseComment;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewComment extends BaseComment{
	@ManyToOne
	private ReviewBoard board;
}
