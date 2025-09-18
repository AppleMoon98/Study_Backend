package com.mallppang.review;

import com.mallppang.base.CommentDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ReviewCommentDTO extends CommentDTO{
	private String writer;
}
