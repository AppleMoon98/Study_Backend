package com.mallppang.chat.entity;

import java.time.Instant;

import jakarta.persistence.*;
import lombok.*;

// uniqueConstraints = (room_id, user_id)
// DB측에서 중복을 막음 // 그냥 우리가 쓰던 유니크 맞음
// 중복으로 접속할 경우를 대비해서 이 코드를 사용했음

@Entity
@Table(uniqueConstraints = @UniqueConstraint(name = "uk_chat_member_room_user", columnNames = { "room_id", "uid" }), indexes = {
		@Index(name = "idx_chat_member_user", columnList = "uid"), @Index(name = "idx_chat_member_room", columnList = "room_id") })
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMember {
	// id - 그 ID 맞음
	// roomId - Member에서 최대 길이 64로 설정해서 똑같이 설정함
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 64, nullable = false)
	private String roomId;

	@Column(nullable = false)
	private Long uid;

	@Column(nullable = false, updatable = false)
	private Instant joinedAt;

	@PrePersist
	void onCreate() {
		if (joinedAt == null)
			joinedAt = Instant.now();
	}
}
