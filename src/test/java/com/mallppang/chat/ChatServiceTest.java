package com.mallppang.chat;

import java.time.Instant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mallppang.chat.entity.ChatMember;
import com.mallppang.chat.entity.ChatRoom;
import com.mallppang.chat.repository.ChatMemberRepository;
import com.mallppang.chat.repository.ChatRoomRepository;
import com.mallppang.chat.service.ChatService;

@SpringBootTest
public class ChatServiceTest {
	@Autowired
	ChatService chatService;
	
	@Autowired
	ChatRoomRepository roomRepository;
	
	@Autowired
	ChatMemberRepository memberRepository;

	@BeforeEach
	void setUp() {
		String rid = "t123";
		
		// 이미 있다면 중복 생성 방지 조건문
		if(!roomRepository.existsById(rid)) {
			roomRepository.save(ChatRoom.builder()
					.id(rid)
					.uid(101L)
					.sellerId(201L)
					.createDate(Instant.now())
					.build());
			memberRepository.save(ChatMember.builder().roomId(rid).uid(101L).build());
			memberRepository.save(ChatMember.builder().roomId(rid).uid(201L).build());
		}
	}
	
	@Test
	void msgTest() {
		String rid = "t123";

		// 가짜 사용자 101이 room t123에 전송
		var dto = chatService.send(101L, rid, "hello", MsgType.TEXT, "uuid-1");

		// 읽었다고 갱신
		chatService.markRead(201L, rid, dto.getId());

		// 읽지 않았을 때 표시할 값 → 이미 확인해서 0 출력
		long unread = chatService.unreadCount(rid, 101L);
		System.err.println(unread);
		
		// uuid를 다르게해서 보낸후 값 → 읽기 처리를 안해서 1 출력
		var dto2 = chatService.send(101L, rid, "hel", MsgType.TEXT, "uuid-2");
		unread = chatService.unreadCount(rid, 201L);
		System.err.println(unread);
	}
}
