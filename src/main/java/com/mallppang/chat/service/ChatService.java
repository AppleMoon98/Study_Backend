package com.mallppang.chat.service;

import org.springframework.data.domain.Page;

import com.mallppang.chat.MsgType;

public interface ChatService {
	// 메세지 전송 (멱등 처리 포함)
	ChatMessageDTO send(Long meId, String roomId, String content, MsgType type, String clientMsgId);
	
	// 읽었는지 갱신
	void markRead(Long meId, String roomId, Long lastReadMessageId);
	
	// 내게 온 읽지않은 메세지 개수
	long unreadCount(String roomId, Long meId);
	
	// 대화했던 채팅방 페이지 조회
	Page<ChatMessageDTO> history(String roomId, int page, int size, Long meId);
	
	// 타이핑 중인지 체크
	void updateTyping(Long meId, String roomId);
}
