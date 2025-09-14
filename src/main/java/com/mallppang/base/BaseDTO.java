package com.mallppang.base;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
public class BaseDTO {
	private Long id;
	private String title;
	private String content;
	private boolean delFlag;
	private LocalDateTime createDate;
	private Long uid;
	
	@Builder.Default
	private List<MultipartFile> files = new ArrayList<>();

	@Builder.Default
	private List<String> uploadFileNames = new ArrayList<>();
}
