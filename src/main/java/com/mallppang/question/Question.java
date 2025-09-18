package com.mallppang.question;

import java.util.ArrayList;
import java.util.List;

import com.mallppang.base.BaseBoard;
import com.mallppang.base.BoardImage;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
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
	@CollectionTable(name = "question_image", joinColumns = @JoinColumn(name= "question_id"))
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
