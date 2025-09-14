package com.mallppang.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mallppang.chat.entity.ChatRoom;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, String>{
	// 방 존재 및 소유자 확인 등 사용할 예정
	boolean existsByIdAndUidOrIdAndSellerId(String id1, Long uid, String id2, Long SellerId);
}
