package com.example.demo.websocket.entity;

import com.example.demo.jpa.support.BaseEntity;
import com.example.demo.websocket.dto.MessageType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * packageName    : com.example.demo.websocket.entity
 * fileName       : ChatMessage
 * author         : JAEIK
 * date           : 11/19/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 11/19/24       JAEIK       최초 생성
 */
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Table(schema = "chat")
public class ChatMessage extends BaseEntity {
    @Enumerated(EnumType.STRING)
    private MessageType type;
    private String sender;
    private String message;
    private Instant createAt;
}
