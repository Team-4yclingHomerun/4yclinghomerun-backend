package com.example.demo.websocket.config.handler;

import com.example.demo.websocket.service.ChatEventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
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
    private final ChatEventService chatEventService;

    @Override
    public void onApplicationEvent(SessionDisconnectEvent event) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());

        Map<String, Object> sessionAttributes = accessor.getSessionAttributes();
        if (sessionAttributes == null) {
            return;
        }

        String nickname = (String) sessionAttributes.get("sender");

        chatEventService.leave(nickname, accessor.getSessionId());
        log.info("채팅방 leave 시도: nickname={}, session={}",
                nickname, accessor.getSessionId());
    }
}
