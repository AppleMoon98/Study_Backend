package com.mallppang.chat.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mallppang.chat.entity.ChatMessage;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long>{
	Page<ChatMessage> findByRoomIdOrderByIdDesc(String roomId, Pageable pageable);
	boolean existsByRoomIdAndClientMsgId(String roomId, String clientMsgId);
	Optional<ChatMessage> findByRoomIdAndClientMsgId(String roomId, String clientMsgId);
	
	// 읽지 않았을 때 : 내가 보낸 메세지 이후 & 상대가 보낸 것
	long countByRoomIdAndIdGreaterThanAndSenderIdNot(String roomId, Long lastReadMessageId, Long myId);
}
