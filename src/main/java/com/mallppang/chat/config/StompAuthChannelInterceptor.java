package com.mallppang.chat.config;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import com.mallppang.chat.repository.ChatMemberRepository;
import com.mallppang.member.Member;
import com.mallppang.member.MemberRepository;
import com.mallppang.util.JWTUtil;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class StompAuthChannelInterceptor implements ChannelInterceptor {
	private final ChatMemberRepository chatMemberRepository;
	private final MemberRepository memberRepository;

	@Override
	public Message<?> preSend(Message<?> message, MessageChannel channel) {
		StompHeaderAccessor acc = StompHeaderAccessor.wrap(message);

		// 1) CONNECT - Authorization 헤더에서 JWT 검증 후, Principal(uid) 주입
		if (acc.getCommand() == StompCommand.CONNECT) {
			String token = firstOrNull(acc.getNativeHeader("Authorization"));
			if (token == null)
				throw new MessagingException("Authorization Header 에러");
			
			Map<String, Object> claims = JWTUtil.validateToken(token);
			String email = (String) claims.get("email");
			if (email == null)
				throw new MessagingException("JWT Email Claim 에러");
			
			Member m = memberRepository.getWithRoles(email);
			if(m == null)
				throw new MessagingException("찾을 수 없는 유저 : " + email);
			
			// Principal 이름 = uid 문자열
			acc.setUser(new UsernamePasswordAuthenticationToken(String.valueOf(m.getUid()), null, List.of()));
			return MessageBuilder.createMessage(message.getPayload(), acc.getMessageHeaders());
		}

		Principal p = acc.getUser();
		if (p == null)
			throw new MessagingException("인증되지 않음");

		String dest = acc.getDestination();
		if (dest == null)
			return message;

		Long uid = parseUid(p.getName());

		// /topic/rooms.{roomId}[.messages|.events]
		if (dest.startsWith("/topic/rooms.")) {
			String roomId = extractRoomFromTopic(dest);
			ensureMember(roomId, uid);
		}

		// /app/chat[.send|.read|.typing]
		if (dest.equals("/app/chat.send") || dest.equals("/app/chat.read") || dest.equals("/app/chat/typing")) {
			String roomId = PayloadRoomExtractor.tryExtractRoomId(message.getPayload());
			if(roomId != null)
				ensureMember(roomId, uid);
		}

		return message;
	}

	private void ensureMember(String roomId, Long uid) {
		if (!chatMemberRepository.existsByRoomIdAndUid(roomId, uid))
			throw new MessagingException("접근 불가 : " + roomId);
	}

	private String extractRoomFromTopic(String dest) {
		String s = dest.substring("/topic/rooms.".length());
		int dot = s.indexOf('.');
		return dot >= 0 ? s.substring(0, dot) : s;
	}

	private Long parseUid(String name) {
		try {
			return Long.valueOf(name);
		} catch (NumberFormatException e) {
			throw new MessagingException("잘못된 UID : " + name);
		}
	}
	
	private static String firstOrNull(List<String> v) {
		return (v==null || v.isEmpty() ? null : v.get(0));
	}
}
