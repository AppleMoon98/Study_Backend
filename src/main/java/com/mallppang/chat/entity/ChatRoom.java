package com.mallppang.chat.entity;

import java.time.Instant;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatRoom {
	// 한 계정이 여러 방에 참가할 가능성이 있기에, 여러 방을 가질 수 있도록 설계
	// 고객과 판매자 id를 보관하여 권한 검증에 사용
	
	// id - 주문ID를 기반으로 채팅방 이름을 생성, room id는 서비스에서 결정할거임
	// uid - 유저 아이디를 가져옴
	// sellerId - 판매자 아이디를 가져옴
	// createDate - 생성 시간
	// onCreate - 생성될 때 생성 시간 설정
	
	@Id
	@Column(length = 64)
	private String id;
	
	@Column(nullable = false)
	private Long uid;
	
	@Column(nullable = false)
	private Long sellerId;
	
	@Column(nullable = false, updatable = false)
	private Instant createDate;
	
	@PrePersist
	void onCreate() {
		if (createDate == null)
			createDate = Instant.now();
	}
}
