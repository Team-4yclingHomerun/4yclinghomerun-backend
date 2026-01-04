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
        Instant createAt,
        boolean isMine
) {

    public static ChatMessageResponse talk(ChatMessage chatMessage, String sender, boolean isMine) {
        return new ChatMessageResponse(
                MessageType.TALK,
                sender,
                chatMessage.getMessage(),
                chatMessage.getCreateAt(),
                isMine
        );
    }

    public static ChatMessageResponse join(String sender) {
        return new ChatMessageResponse(
                MessageType.JOIN,
                sender,
                sender + " 채팅방에 참여했습니다.",
                Instant.now(),
                false

        );
    }

    public static ChatMessageResponse leave(String sender) {
        return new ChatMessageResponse(
                MessageType.LEAVE,
                sender,
                sender + " 채팅방을 나갔습니다.",
                Instant.now(),
                false
        );
    }
}
