package com.example.demo.websocket;

import com.example.demo.websocket.dto.ChatMessage;
import com.example.demo.websocket.dto.ChatRoom;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.PongMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * packageName    : com.example.demo.websocket
 * fileName       : WebSocketChatHandler
 * author         : JAEIK
 * date           : 10/28/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/28/24        JAEIK       최초 생성
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class WebSocketChatHandler extends TextWebSocketHandler {
    private final ObjectMapper objectMapper;
    private final ChatService chatService;

    // 멀티스레드 환경에서 안전하게 세션 관리
    private static final Map<String, WebSocketSession> sessionMap = new ConcurrentHashMap<>();

    // 클라이언트로부터 수신한 텍스트 받고 , 서버에서 클라이언트에게 메시지 응답
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.info("payload:{}", payload);
        // TextMessage textMessage = new TextMessage("Welcome chatting server");
        // session.sendMessage(textMessage);
        ChatMessage chatMessage = objectMapper.readValue(payload, ChatMessage.class);
        ChatRoom room = chatService.findRoomById(chatMessage.getRoomId());
        room.handleAction(session,chatMessage,chatService);
    }

    // sessionMap 연결 정보 세션 저장
    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        sessionMap.put(session.getId(), session);

    }

    // sessionMap 연결 정보 삭제
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        sessionMap.remove(session.getId());
    }
}
