package com.example.demo.websocket.dto;

import com.example.demo.websocket.entity.ChatMessage;
import org.springframework.data.domain.Slice;

import java.time.Instant;

/**
 * packageName    : com.example.demo.websocket.dto
 * fileName       : ChatMessageResponse
 * author         : JAEIK
 * date           : 11/22/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 11/22/24       JAEIK       최초 생성
 */
public record ChatMessageResponse(
        MessageType type,
        String sender,
        String message,
        Instant createAt
) {
}
