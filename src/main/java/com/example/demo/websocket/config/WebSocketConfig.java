package com.example.demo.websocket.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.*;

import java.util.List;

/**
 * packageName    : com.example.demo.websocket
 * fileName       : WebSocketConfig
 * author         : JAEIK
 * date           : 10/28/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/28/24        JAEIK       최초 생성
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig  implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // 클라이언트가 구독할 경로 /topic
        config.enableSimpleBroker("/topic");
        // 클라이언트가 서버로 메시지를 보낼 경로 /pub
        config.setApplicationDestinationPrefixes("/pub");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/api/ws-stomp")
                .setAllowedOriginPatterns("*")
                .withSockJS();
    }
}


    //  private final WebSocketHandler webSocketHandler;
    // STOMP 사용전의  WebSocketConfigurer 설정
//    @Override
//    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//        registry.addHandler(webSocketHandler, "/ws/chat").setAllowedOrigins("*");
//    }

