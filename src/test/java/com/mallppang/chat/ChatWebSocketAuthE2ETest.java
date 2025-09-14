package com.mallppang.chat;

import com.mallppang.chat.entity.ChatMember;
import com.mallppang.chat.entity.ChatRoom;
import com.mallppang.chat.repository.ChatMemberRepository;
import com.mallppang.chat.repository.ChatRoomRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.*;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.*;

import java.lang.reflect.Type;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ChatWebSocketAuthE2ETest {

	@LocalServerPort
	int port;

	@Autowired
	ChatRoomRepository roomRepo;

	@Autowired
	ChatMemberRepository memberRepo;

	WebSocketStompClient client;
	String rid = "wsE2E1";

	@BeforeEach
	void setUp() {
		client = new WebSocketStompClient(
				new SockJsClient(List.of(new WebSocketTransport(new StandardWebSocketClient()))));
		client.setMessageConverter(new MappingJackson2MessageConverter());

		// 방/멤버 준비
		if (!roomRepo.existsById(rid)) {
			roomRepo.save(ChatRoom.builder().id(rid).uid(101L) // 고객 uid
					.sellerId(201L) // 판매자 uid
					.createDate(Instant.now()).build());
			memberRepo.save(ChatMember.builder().roomId(rid).uid(101L).build());
			memberRepo.save(ChatMember.builder().roomId(rid).uid(201L).build());
		}
	}

	@Test
	void connect_with_jwt_then_send_and_receive() throws Exception {
		// 유효한 토큰값 받아와서 적을것
		String token = "eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJwYXNzd29yZCI6IiQyYSQxMCRuQ0pPRzZ2SWxsRHFhalowU3pGVEllNHZrcjNGUmJ1OEdwaktDMExqUU1mN0VReGNocjBESyIsInNvY2lhbCI6ZmFsc2UsIm5pY2tuYW1lIjoibWVtYmVyOSIsInJvbGVOYW1lcyI6WyJNRU1CRVIiLCJTRUxMRVIiLCJBRE1JTiJdLCJlbWFpbCI6IjEyMyIsImlhdCI6MTc1NzM4NDYwOCwiZXhwIjoxNzU3Mzg1MjA4fQ.yvbWPJb2qypb1GGpTNy9ZXPWpWFmT8DUqGTmpSmDet4";
		String url = "http://localhost:" + port + "/ws-chat";
		
		// 1) CONNECT (Authorization 헤더 포함)
		WebSocketHttpHeaders handshakeHeaders = new WebSocketHttpHeaders();
		StompHeaders connectHeaders = new StompHeaders();
		connectHeaders.add("Authorization", "Bearer " + token);
		
		StompSession session = client
				.connect(url, handshakeHeaders, connectHeaders, new StompSessionHandlerAdapter() {})
				.get(3, TimeUnit.SECONDS);
		
		// /topic/rooms.{roomId}.gessages
		BlockingQueue<Map<String, Object>> received = new ArrayBlockingQueue<>(1);
		session.subscribe("/topic/rooms." + rid + ".messages", new StompFrameHandler() {
			
			@Override
			public void handleFrame(StompHeaders headers, Object payload) {
				@SuppressWarnings("unchecked")
				Map<String, Object> map = (Map<String, Object>) payload;
				received.offer(map);
			}
			
			@Override
			public Type getPayloadType(StompHeaders headers) {
				return Map.class;
			}
		});
		
		// /app/chat.send - Map payload 사용
		String clientMsgId = "uuid-e2e-" + System.nanoTime();
		Map<String, Object> body = new HashMap<>();
		body.put("roomId", rid);
		body.put("content", "is message test");
		body.put("type", "TEXT");
		body.put("clientMsgId", clientMsgId);
		
		session.send("/app/chat.send", body);
		
		// 수신 확인
		Map<String, Object> msg = received.poll(3, TimeUnit.SECONDS);
		assertThat(msg).as("브로드캐스트 메세지 수신").isNotNull();
		assertThat(msg.get("roomId")).isEqualTo(rid);
		assertThat(msg.get("content")).isEqualTo("is message test");
		assertThat(msg.get("clientMsgId")).isEqualTo(clientMsgId);
	}
}
