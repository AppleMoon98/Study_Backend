package com.mallppang.chat.service;

import java.time.Instant;

import com.mallppang.chat.MsgStatus;
import com.mallppang.chat.MsgType;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessageDTO {
	private Long id;
	private String roomId;
	private Long senderId;
	private Long receiverId;
	private MsgType type;
	private String content;
	private MsgStatus status;
	private String clientMsgId;
	private Instant createDate;
}
