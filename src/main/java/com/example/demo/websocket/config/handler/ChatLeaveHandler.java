package com.example.demo.websocket.config.handler;

import com.example.demo.websocket.dto.ChatMessageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.Map;

/**
 * packageName    : com.example.demo.websocket.config.handler
 * fileName       : ChatLeaveHander
 * author         : JAEIK
 * date           : 1/4/26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 1/4/26        JAEIK       최초 생성
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ChatLeaveHandler implements ApplicationListener<SessionDisconnectEvent> {
    private final SimpMessageSendingOperations simpMessageSendingOperations;

    @Override
    public void onApplicationEvent(SessionDisconnectEvent event) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());

        Map<String, Object> sessionAttributes = accessor.getSessionAttributes();
        if (sessionAttributes == null) {
            return;
        }

        String nickname = (String) sessionAttributes.get("sender");

        simpMessageSendingOperations.convertAndSend("/topic/chat/room", ChatMessageResponse.leave(nickname));
        log.info("채팅방을 나갔습니다. {}", ChatMessageResponse.leave(nickname));
    }
}
