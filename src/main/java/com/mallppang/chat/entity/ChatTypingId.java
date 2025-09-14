package com.mallppang.chat.entity;

import java.io.Serializable;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ChatTypingId implements Serializable{
	private String roomId;
	private Long uid;
}
