package com.mallppang.chat.service;

import java.time.Instant;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mallppang.chat.MsgStatus;
import com.mallppang.chat.MsgType;
import com.mallppang.chat.entity.ChatMessage;
import com.mallppang.chat.entity.ChatRead;
import com.mallppang.chat.entity.ChatReadId;
import com.mallppang.chat.entity.ChatRoom;
import com.mallppang.chat.entity.ChatTyping;
import com.mallppang.chat.entity.ChatTypingId;
import com.mallppang.chat.repository.ChatMemberRepository;
import com.mallppang.chat.repository.ChatMessageRepository;
import com.mallppang.chat.repository.ChatReadRepository;
import com.mallppang.chat.repository.ChatRoomRepository;
import com.mallppang.chat.repository.ChatTypingRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ChatServiceImpl implements ChatService{
	private final ChatRoomRepository roomRepository;
	private final ChatMemberRepository memberRepository;
	private final ChatMessageRepository messageRepository;
	private final ChatReadRepository readRepository;
	private final ChatTypingRepository typingRepository;

	@Override
	public ChatMessageDTO send(Long meId, String roomId, String content, MsgType type, String clientMsgId) {
		// 유저/채팅방 검증
		ensureMember(roomId, meId);
		
		// 멱등 체크 : 중복 roomId, clientMsgId 발생시 기존 메세지 반환
		Optional<ChatMessage> dump = findByRoomIdAndClientMsgId(roomId, clientMsgId);
		if(dump.isPresent())
			return entityToDTO(dump.get());
		
		// 수신 설정
		Long peerId = resolvePeerId(roomId, meId);
		
		// 저장
		ChatMessage save = messageRepository.save(ChatMessage.builder()
				.roomId(roomId)
				.senderId(meId)
				.receiverId(peerId)
				.type(type != null ? type : MsgType.TEXT)
				.content(content)
				.status(MsgStatus.SENT)
				.clientMsgId(clientMsgId)
				.createDate(Instant.now())
				.build());
		return entityToDTO(save);
	}

	@Override
	public void markRead(Long meId, String roomId, Long lastReadMessageId) {
		// 유저/채팅방 검증
		ensureMember(roomId, meId);
		
		ChatReadId id = new ChatReadId(roomId, meId);
		ChatRead chatRead = readRepository.findById(id)
				.orElseGet(() -> ChatRead.builder()
						.roomId(roomId)
						.uid(meId)
						.lastReadMessageId(0L)
						.build());
		
		// 뒤로 가지 않도록 max 적용
		if(lastReadMessageId != null && lastReadMessageId > (chatRead.getLastReadMessageId() == null ? 0 : chatRead.getLastReadMessageId()))
			chatRead.setLastReadMessageId(lastReadMessageId);
		
		chatRead.setUpdateDate(Instant.now());
		readRepository.save(chatRead);
	}

	@Override
	@Transactional(readOnly = true)
	public long unreadCount(String roomId, Long meId) {
		// 유저/채팅방 검증
		ensureMember(roomId, meId);
		
		long lastRead = readRepository.findByRoomIdAndUid(roomId, meId)
				.map(ChatRead::getLastReadMessageId)
				.orElse(0L);
		
		// 상대가 보낸 내(meId)가 안 읽은 메세지 수
		return messageRepository.countByRoomIdAndIdGreaterThanAndSenderIdNot(roomId, lastRead, meId);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<ChatMessageDTO> history(String roomId, int page, int size, Long meId) {
		// 유저/채팅방 검증
		ensureMember(roomId, meId);
		
		Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
		Page<ChatMessage> result = messageRepository.findByRoomIdOrderByIdDesc(roomId, pageable);
		return result.map(this::entityToDTO);
	}

	@Override
	public void updateTyping(Long meId, String roomId) {
		// 유저/채팅방 검증
		ensureMember(roomId, meId);
		
		ChatTypingId id = new ChatTypingId(roomId, meId);
		ChatTyping chatTyping = typingRepository.findById(id)
				.orElseGet(() -> ChatTyping.builder()
						.roomId(roomId)
						.uid(meId)
						.build());
		chatTyping.setUpdateDate(Instant.now());
		typingRepository.save(chatTyping);
	}
	
	/*---------------------메서드---------------------*/
	private void ensureMember(String roomId, Long meId) {
		// 방 존재 확인
		roomRepository.findById(roomId).orElseThrow(() ->
				new NoSuchElementException("방을 찾을 수 없음 : " + roomId));
		
		// 유저 체크
		if(!memberRepository.existsByRoomIdAndUid(roomId, meId))
			throw new SecurityException("접근 권한이 없음 : " + roomId);
	}
	
	private Long resolvePeerId(String roomId, Long meId) {
		ChatRoom chatRoom = roomRepository.findById(roomId).orElseThrow();
		if(chatRoom.getUid().equals(meId))
			return chatRoom.getSellerId();
		if(chatRoom.getSellerId().equals(meId))
			return chatRoom.getUid();
		throw new SecurityException("접근 권한이 없음 : " + roomId);
	}
	
	private Optional<ChatMessage> findByRoomIdAndClientMsgId(String roomId, String clientMsgId){
		try {
			return messageRepository.findByRoomIdAndClientMsgId(roomId, clientMsgId);
		} catch (NoSuchMethodError e) {
			if(messageRepository.existsByRoomIdAndClientMsgId(roomId, clientMsgId)) {
				Page<ChatMessage> p = messageRepository.findByRoomIdOrderByIdDesc(roomId, PageRequest.of(0, 1));
				if(!p.isEmpty() && clientMsgId.equals(p.getContent().get(0).getClientMsgId()))
					return Optional.of(p.getContent().get(0));
			}
			return Optional.empty();
		} catch (DataIntegrityViolationException e) {
			return Optional.empty();
		}
	}
	
	private ChatMessageDTO entityToDTO(ChatMessage entity) {
		return ChatMessageDTO.builder()
					.id(entity.getId())
					.roomId(entity.getRoomId())
					.senderId(entity.getSenderId())
					.receiverId(entity.getReceiverId())
					.type(entity.getType())
					.content(entity.getContent())
					.status(entity.getStatus())
					.clientMsgId(entity.getClientMsgId())
					.createDate(entity.getCreateDate())
					.build();
	}
}
