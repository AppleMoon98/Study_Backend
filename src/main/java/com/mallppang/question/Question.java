package com.mallppang.question;

import java.util.ArrayList;
import java.util.List;

import com.mallppang.base.BaseBoard;
import com.mallppang.base.BoardImage;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Question extends BaseBoard{
	@OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
	private List<QuestionComment> commentList;
	
	@ElementCollection
	@CollectionTable(name = "question_image", joinColumns = @JoinColumn(name = "question_board_id"))
	@Builder.Default
	private List<BoardImage> imageList = new ArrayList<>();
	
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
