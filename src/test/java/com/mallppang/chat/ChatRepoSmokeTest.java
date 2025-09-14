package com.mallppang.chat;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.mallppang.chat.entity.ChatMember;
import com.mallppang.chat.entity.ChatMessage;
import com.mallppang.chat.entity.ChatRead;
import com.mallppang.chat.entity.ChatRoom;
import com.mallppang.chat.entity.ChatTyping;
import com.mallppang.chat.repository.ChatMemberRepository;
import com.mallppang.chat.repository.ChatMessageRepository;
import com.mallppang.chat.repository.ChatReadRepository;
import com.mallppang.chat.repository.ChatRoomRepository;
import com.mallppang.chat.repository.ChatTypingRepository;


// 대충 채팅 레포지토리 테스트한 흔적입니다.

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class ChatRepoSmokeTest {
	@Autowired
	ChatRoomRepository roomRepository;
	
	@Autowired
	ChatMemberRepository memberRepository;
	
	@Autowired
	ChatMessageRepository messageRepository;
	
	@Autowired
	ChatReadRepository readRepository;
	
	@Autowired
	ChatTypingRepository typingRepository;

	@Test
	void end_to_end_minimal() {
		String rid = "t123";
		
		// 대화할 방 생성
		roomRepository.save(ChatRoom.builder()
								.id(rid)
								.uid(101L)
								.sellerId(201L)
								.createDate(Instant.now())
								.build());
		
		// 방에 유저를 추가
		memberRepository.save(ChatMember.builder().roomId(rid).uid(101L).build());
		memberRepository.save(ChatMember.builder().roomId(rid).uid(201L).build());
		
		// 메세지 저장 + 멱등키 테스트
		var msg1 = messageRepository.save(ChatMessage.builder()
				.roomId(rid)
				.senderId(101L)
				.receiverId(201L)
				.type(MsgType.TEXT)
				.content("hello")
				.status(MsgStatus.SENT)
				.clientMsgId("uuid-1")
				.createDate(Instant.now())
				.build());
		assertThat(messageRepository.existsByRoomIdAndClientMsgId(rid, "uuid-1"));
		
		// 읽음 체크
		readRepository.save(ChatRead.builder()
				.roomId(rid)
				.uid(101L)
				.lastReadMessageId(msg1.getId())
				.updateDate(Instant.now())
				.build());
		var cr = readRepository.findByRoomIdAndUid(rid, 101L).orElseThrow();
		assertThat(cr.getLastReadMessageId()).isEqualTo(msg1.getId());
		
		// 타이핑 상태 체크
		typingRepository.save(ChatTyping.builder().roomId(rid).uid(201L).updateDate(Instant.now()).build());
		assertThat(typingRepository.findByRoomId(rid)).hasSize(1);
	}
}
