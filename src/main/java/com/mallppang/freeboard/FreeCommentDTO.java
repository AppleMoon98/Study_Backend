package com.mallppang.freeboard;

import com.mallppang.base.CommentDTO;

import lombok.Builder;
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
public class FreeCommentDTO extends CommentDTO{
	private String writer;
	private String email;
}
