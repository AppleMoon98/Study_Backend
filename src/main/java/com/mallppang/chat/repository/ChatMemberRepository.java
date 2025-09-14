package com.mallppang.chat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mallppang.chat.entity.ChatMember;

public interface ChatMemberRepository extends JpaRepository<ChatMember, Long>{
	boolean existsByRoomIdAndUid(String roomId, Long uid);
	List<ChatMember> findByUid(Long uid);
	List<ChatMember> findByRoomId(String roomId);
	long deleteByRoomIdAndUid(String roomId, Long uid);
}
