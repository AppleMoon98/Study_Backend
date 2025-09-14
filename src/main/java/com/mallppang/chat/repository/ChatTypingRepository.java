package com.mallppang.chat.repository;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mallppang.chat.entity.ChatTyping;
import com.mallppang.chat.entity.ChatTypingId;

public interface ChatTypingRepository extends JpaRepository<ChatTyping, ChatTypingId>{
	List<ChatTyping> findByRoomId(String roomId);
	long deleteByRoomIdAndUid(String roomId, Long uid);
	
	// 오래된 내용 정리용도
	long deleteByRoomIdAndUpdateDateBefore(String roomId, Instant before);
}