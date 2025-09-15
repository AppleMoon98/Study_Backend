package com.mallppang.question;

import com.mallppang.base.CommentDTO;

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
public class QuestionCommentDTO extends CommentDTO{
	private String writer;
}
