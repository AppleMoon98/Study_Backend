package com.mallppang.chat.entity;

import java.time.Instant;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(indexes = @Index(name = "idx_char_read_room", columnList = "room_id"))
@IdClass(ChatReadId.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatRead {
	@Id
	@Column(length = 64, nullable = false)
	private String roomId;
	
	@Id
	@Column(nullable = false)
	private Long uid;
	
	@Column(nullable = false)
	private Long lastReadMessageId;
	
	@Column(nullable = false)
	private Instant updateDate;
	
	@PrePersist
	@PreUpdate
	void touch() {
		updateDate = Instant.now();
	}
}
