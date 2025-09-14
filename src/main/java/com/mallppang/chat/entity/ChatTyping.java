package com.mallppang.chat.entity;

import java.io.Serializable;
import java.time.Duration;
import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Index;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(indexes = @Index(name = "idx_chat_typing_room", columnList = "room_id"))
@IdClass(ChatTypingId.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatTyping {
	@Id
	@Column(length = 64, nullable = false)
	private String roomId;
	
	@Id
	@Column(nullable = false)
	private Long uid;
		
	@Column(nullable = false)
	private Instant updateDate;
	
	@PrePersist
	@PreUpdate
	void touch() {
		updateDate = Instant.now();
	}
	
	public boolean isTypingNow(Duration window) {
		return updateDate != null && updateDate.isAfter(Instant.now().minus(window));
	}
}
