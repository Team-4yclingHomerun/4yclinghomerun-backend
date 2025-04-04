package com.example.demo.websocket.mapper;

import com.example.demo.websocket.dto.ChatMessageRequest;
import com.example.demo.websocket.dto.ChatMessageResponse;
import com.example.demo.websocket.entity.ChatMessage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

/**
 * packageName    : com.example.demo.websocket.mapper
 * fileName       : ChatMessageDtoMapper
 * author         : JAEIK
 * date           : 11/19/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 11/19/24       JAEIK       최초 생성
 */
@Mapper(componentModel = "spring")
public interface ChatMessageDtoMapper {
    @Mapping(target = "createAt", expression = "java(java.time.Instant.now())")
    ChatMessage convertToEntity(ChatMessageRequest messageRequest, UUID senderId);

    List<ChatMessageResponse> toChatMessageResponseList(List<ChatMessage> chatMessages);
}
