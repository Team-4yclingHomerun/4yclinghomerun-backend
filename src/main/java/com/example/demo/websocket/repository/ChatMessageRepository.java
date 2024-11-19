package com.example.demo.websocket.repository;

import com.example.demo.websocket.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * packageName    : com.example.demo.websocket.repository
 * fileName       : ChatMessageRepository
 * author         : JAEIK
 * date           : 11/19/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 11/19/24       JAEIK       최초 생성
 */
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
}
