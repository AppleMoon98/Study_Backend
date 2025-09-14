package com.mallppang.chat.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mallppang.chat.entity.ChatRead;
import com.mallppang.chat.entity.ChatReadId;

public interface ChatReadRepository extends JpaRepository<ChatRead, ChatReadId>{
	Optional<ChatRead> findByRoomIdAndUid(String roomId, Long uid);
}
