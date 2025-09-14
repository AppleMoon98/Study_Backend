package com.mallppang.chat.entity;

import java.time.Instant;

import com.mallppang.chat.MsgStatus;
import com.mallppang.chat.MsgType;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(name = "uk_chat_message_room_client", columnNames = { "room_id",
		"client_msg_id" }), indexes = { @Index(name = "idx_chat_message_room_id", columnList = "room_id"),
				@Index(name = "idx_chat_message_room_create_date", columnList = "room_id, create_date"),
				@Index(name = "idx_chat_message_sender", columnList = "sender_id") })
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 64, nullable = false)
	private String roomId;

	@Column(nullable = false)
	private Long senderId;

	@Column(nullable = false)
	private Long receiverId;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 16)
	private MsgType type;

	@Lob
	@Column(nullable = false)
	private String content;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 16)
	private MsgStatus status;

	@Column(nullable = false, length = 64)
	private String clientMsgId;

	@Column(nullable = false, updatable = false)
	private Instant createDate;

	void onCreate() {
		if (createDate == null)
			createDate = Instant.now();

		if (status == null)
			status = MsgStatus.SENT;

		if (type == null)
			type = MsgType.TEXT;
	}
}
