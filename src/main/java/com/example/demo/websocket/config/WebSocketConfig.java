package com.example.demo.websocket.config;



import com.example.demo.websocket.config.handler.StompHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;


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
@RequiredArgsConstructor
@EnableWebSocketMessageBroker
public class WebSocketConfig  implements WebSocketMessageBrokerConfigurer {

    private final StompHandler stompHandler;

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

    /*
     - 클라이언트가 서버로 보내는 메시지를 처리하는 채널
     - 메시지 처리 전에 Interceptor 메시지를 가로채어 추가적인 토큰검증 작업 수행
     */
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(stompHandler);
    }
}


    //  private final WebSocketHandler webSocketHandler;
    // STOMP 사용전의  WebSocketConfigurer 설정
//    @Override
//    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//        registry.addHandler(webSocketHandler, "/ws/chat").setAllowedOrigins("*");
//    }

