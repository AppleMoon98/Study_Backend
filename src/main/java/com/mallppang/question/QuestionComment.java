package com.mallppang.question;

import com.mallppang.base.BaseComment;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionComment extends BaseComment{
	@ManyToOne(fetch = FetchType.LAZY)
	private Question board;
}
