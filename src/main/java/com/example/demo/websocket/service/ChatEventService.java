package com.example.demo.websocket.service;

import com.example.demo.websocket.dto.ChatMessageResponse;
import com.example.demo.websocket.repository.ChatSessionRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

/**
 * packageName    : com.example.demo.websocket.service
 * fileName       : ChatEventService
 * author         : JAEIK
 * date           : 1/5/26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 1/5/26        JAEIK       최초 생성
 */
@Service
@RequiredArgsConstructor
public class ChatEventService {
    private final ChatSessionRegistry registry;
    private final SimpMessageSendingOperations simpMessageSendingOperations;


    public void join(String nickname, String sessionId) {
        boolean firstJoin = registry.add(nickname, sessionId);

        if (firstJoin) {
            simpMessageSendingOperations.convertAndSend("/topic/chat/room",ChatMessageResponse.join(nickname));
        }
    }

    public void leave(String nickname, String sessionId) {
        boolean lastLeave = registry.remove(nickname, sessionId);

        if (lastLeave) {
            simpMessageSendingOperations.convertAndSend("/topic/chat/room", ChatMessageResponse.leave(nickname));
        }
    }

    public int totalUser() {
        return registry.userCount();
    }
}
