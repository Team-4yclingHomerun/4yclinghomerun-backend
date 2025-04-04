package com.example.demo.websocket.dto;

import com.example.demo.websocket.entity.ChatMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Slice;

import java.time.Instant;
import java.util.UUID;

/**
 * packageName    : com.example.demo.websocket.dto
 * fileName       : ChatMessage
 * author         : JAEIK
 * date           : 10/29/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/29/24        JAEIK       최초 생성
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageRequest {
    private MessageType type;
    private String sender;
    private String message;

    public void updateSender(String sender) {
        this.sender = sender;

    }
    public void setMessage(String message) {
        this.message = message;
    }
}
