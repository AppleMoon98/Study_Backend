package com.mallppang.chat.web;

import java.security.Principal;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.mallppang.chat.MsgType;
import com.mallppang.chat.service.ChatMessageDTO;
import com.mallppang.chat.service.ChatService;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Controller
@RequiredArgsConstructor
public class ChatController {

	private final SimpMessagingTemplate template;
	private final ChatService chatService;

	private Long uid(Principal p) {
		return Long.valueOf(p.getName());
	}

	@MessageMapping("/chat.send")
	public void send(Principal me, SendMessageReq req) {
		ChatMessageDTO saved = chatService.send(uid(me), req.getRoomId(), req.getContent(),
				req.getType() != null ? req.getType() : MsgType.TEXT, req.getClientMsgId());
		// 방 브로드캐스트
		template.convertAndSend("/topic/rooms." + saved.getRoomId() + ".messages", saved);
	}

	@MessageMapping("/chat.read")
	public void read(Principal me, ReadReq req) {
		chatService.markRead(uid(me), req.getRoomId(), req.getLastReadMessageId());
		template.convertAndSend("/topic/rooms." + req.getRoomId() + ".events", RoomEvent.builder().type("READ")
				.roomId(req.getRoomId()).uid(uid(me)).value(req.getLastReadMessageId()).build());
	}

	@MessageMapping("/chat.typing")
	public void typing(Principal me, TypingReq req) {
		chatService.updateTyping(uid(me), req.getRoomId());
		template.convertAndSend("/topic/rooms." + req.getRoomId() + ".events",
				RoomEvent.builder().type("TYPING").roomId(req.getRoomId()).uid(uid(me)).build());
	}

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class SendMessageReq {
		private String roomId;
		private String content;
		private MsgType type;
		private String clientMsgId;
	}

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class ReadReq {
		private String roomId;
		private Long lastReadMessageId;
	}

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class TypingReq {
		private String roomId;
	}

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class RoomEvent {
		private String type;
		private String roomId;
		private Long uid; // 이벤트 발생자 uid
		private Long value;
	}
}
