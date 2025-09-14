package com.mallppang.notice;

import java.time.LocalDateTime;

import com.mallppang.base.BaseDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class NoticeDTO extends BaseDTO{
	private LocalDateTime startDate;
    private LocalDateTime endDate;
}
