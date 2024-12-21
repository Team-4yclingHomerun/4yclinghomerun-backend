package com.example.demo.websocket.repository;

import com.example.demo.websocket.dto.ChatMessageRequest;
import com.example.demo.websocket.dto.MessageType;
import com.example.demo.websocket.entity.ChatMessage;
import com.example.demo.websocket.mapper.ChatMessageDtoMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestConstructor;

import static org.assertj.core.api.Assertions.assertThat;


import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

/**
 * packageName    : com.example.demo.websocket.repository
 * fileName       : ChatMessageRepositoryTest
 * author         : JAEIK
 * date           : 12/19/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 12/19/24       JAEIK       최초 생성
 */
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ChatMessageRepositoryTest {

    @Autowired
    private ChatMessageDtoMapper chatMessageDtoMapper;

    @Autowired
    private ChatMessageRepository chatMessageRepository;
    @Test
    @DisplayName("chatMessage 객체가 잘 저장되는지")
    void convertToEntityTest() {
        // given
        ChatMessageRequest chatMessageRequest = new ChatMessageRequest(MessageType.TALK,"kim","dfdffdas");


        //when
        ChatMessage chatMessage = chatMessageDtoMapper.convertToEntity(chatMessageRequest);
        System.out.println(chatMessage.toString());
        System.out.println("ChatMessage Type: " + chatMessage.getType());
        System.out.println("ChatMessage Type: " + chatMessage.getSender());
        System.out.println("ChatMessage Type: " + chatMessage.getMessage());

        chatMessageRepository.save(chatMessage);

        //Then
        assertThat(chatMessage).isNotNull();
        assertThat(chatMessage.getType()).isEqualTo(MessageType.TALK);
        assertThat(chatMessage.getSender()).isEqualTo("kim");
        assertThat(chatMessage.getMessage()).isEqualTo("dfdffdas");

        // 데이터베이스에 저장된 객체가 제대로 저장되었는지 확인
        ChatMessage savedChatMessage = chatMessageRepository.findById(chatMessage.getId()).orElse(null);
        assertThat(savedChatMessage).isNotNull();
        assertThat(savedChatMessage.getType()).isEqualTo(MessageType.TALK);
        assertThat(savedChatMessage.getSender()).isEqualTo("kim");
        assertThat(savedChatMessage.getMessage()).isEqualTo("dfdffdas");

    }
}