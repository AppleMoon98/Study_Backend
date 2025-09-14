package com.mallppang.base;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
	private Long id;
	private String content;
	private boolean delFlag;
	private Long boardId;
	
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDateTime createDate;
}
