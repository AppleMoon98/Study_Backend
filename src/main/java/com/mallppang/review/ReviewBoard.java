package com.mallppang.review;

import java.util.ArrayList;
import java.util.List;

import com.mallppang.base.BaseBoard;
import com.mallppang.base.BoardImage;
import com.mallppang.member.Member;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class ReviewBoard extends BaseBoard{	
	
	@Builder.Default
	private Integer view = 0;
	
	@ManyToOne
	@JoinColumn(name = "uid")
    private Member member;
	
	
	// 양방향 참조 때문에 무한 루프를 도는 것일 수 있음
	// 수정 가능한지 확인하고 안되면 다른 방향 찾아보기
	// 현재 가장 가능성이 높은 것은 양방향 참조임 
	@OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
	private List<ReviewComment> commentList;
	
	@ElementCollection
	@CollectionTable(name = "review_image", joinColumns = @JoinColumn(name = "review_board_id"))
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
