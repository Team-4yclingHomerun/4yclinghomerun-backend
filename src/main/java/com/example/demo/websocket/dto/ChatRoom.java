package com.example.demo.websocket.dto;

import com.example.demo.websocket.ChatService;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;

/**
 * packageName    : com.example.demo.websocket.dto
 * fileName       : ChatRoom
 * author         : JAEIK
 * date           : 10/29/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/29/24        JAEIK       최초 생성
 */
@Getter
public class ChatRoom {
    private String roomId;
    private String roomName;
    private Set<WebSocketSession> sessions = new HashSet<>();

    @Builder
    public ChatRoom(String roomId, String roomName) {
        this.roomId = roomId;
        this.roomName = roomName;
    }
    public void handleAction(WebSocketSession socketSession, ChatMessage chatMessage, ChatService chatService) {
        if (chatMessage.getType().equals(MessageType.ENTER)) {
            sessions.add(socketSession);
            chatMessage.setSender(chatMessage.getSender()+ "님이 입장하셨습니다.");
        }
        sendMessage(chatMessage, chatService);
    }
    public <T> void sendMessage(T message, ChatService chatService) {
        // 병렬 스트림을 생성하여, 여러 클라이언트에게 동시에 메시지를 보낼 수 있게한다.
        sessions.parallelStream().forEach(session -> chatService.sendMessage(session, message));
    }
}
