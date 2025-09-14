package com.mallppang.notice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.mallppang.base.BaseBoard;
import com.mallppang.base.BoardImage;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class NoticeBoard extends BaseBoard{
	@ElementCollection
	@CollectionTable(name = "notice_image", joinColumns = @JoinColumn(name = "notice_board_id"))
	@Builder.Default
	private List<BoardImage> imageList = new ArrayList<>();
	private LocalDateTime startDate;
	private LocalDateTime endDate;

	public void addImage(BoardImage image) {
		image.setOrd(this.imageList.size());
		imageList.add(image);
	}

	public void addImageString(String fileName) {
		BoardImage boardImage = BoardImage.builder()
				.fileName(fileName)
				.build();
		addImage(boardImage);
	}

	public void clearImageList() {
		this.imageList.clear();
	}
}
