package com.example.demo.websocket.config.handler;


import com.example.demo.auth.AuthenticateMember;
import com.example.demo.jwt.JwtParser;
import com.example.demo.jwt.JwtProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * packageName    : com.example.demo.websocket.config.handler
 * fileName       : StomHandler
 * author         : JAEIK
 * date           : 11/21/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 11/21/24       JAEIK       최초 생성
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class StompHandler implements ChannelInterceptor {
    private final JwtParser jwtParser;

    // 메시지가 클라이언트에서 브로커로 전달되기 전에 호출 (유호성검사, 토큰 인증 등)
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);

        // webSocket 연결시 헤더의 jwt token 검증
        if (StompCommand.CONNECT == accessor.getCommand()) {
            String token = accessor.getFirstNativeHeader(JwtProperties.ACCESS_TOKEN_HEADER);

            if (token != null && token.startsWith(JwtProperties.ACCESS_TOKEN_PREFIX)) {
                token = token.substring(JwtProperties.ACCESS_TOKEN_PREFIX.length()); // "Bearer " 제거
            }

            AuthenticateMember auth = jwtParser.getAuthenticateMember(token);

            Map<String, Object> sessionAttributes = accessor.getSessionAttributes();
            if (sessionAttributes != null) {
                sessionAttributes.put("Auth", auth);
                log.info(auth.username());
            }
        }
        return message;
    }
}





