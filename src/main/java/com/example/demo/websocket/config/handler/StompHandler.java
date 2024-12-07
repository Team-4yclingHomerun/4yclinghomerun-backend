package com.example.demo.websocket.config.handler;

import com.example.demo.exception.AuthorizationErrorCode;
import com.example.demo.exception.AuthorizationException;
import com.example.demo.jwt.JwtParser;
import com.example.demo.jwt.JwtProperties;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

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
        // validateToken 자체에 예외처리가 있으므로 따로 예외처리 할 필요 없음
        if (StompCommand.CONNECT == accessor.getCommand()) {
            try {
                String token = accessor.getFirstNativeHeader(JwtProperties.ACCESS_TOKEN_HEADER);
                jwtParser.validateToken(token);
                log.debug("Received token: {}", token);
                //jwtParser.validateToken(accessor.getFirstNativeHeader(JwtProperties.ACCESS_TOKEN_HEADER));
            } catch (JwtException e) {
                log.error("연결 오류:", e);
                throw new MessagingException("jwt Token 연결오류", e);
            }
        }
        return message;
    }
}


