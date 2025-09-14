package com.mallppang.chat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

// 나중에 트래픽 커지면 enableStompBrokerRelay()로 RabbitMQ 연결만 바꾸면 됨

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/ws-chat")
			.setAllowedOriginPatterns("*")
			.withSockJS();
		// 초기에는 SockJS fallback 유지
	}
	
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.enableSimpleBroker("/topic", "/queue");		// 구독 prefix
		registry.setApplicationDestinationPrefixes("/app");	// 발행 prefix
		registry.setUserDestinationPrefix("/user");				// convertAndSendToUser용
	}
}
