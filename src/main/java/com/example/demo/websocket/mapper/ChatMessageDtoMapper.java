package com.example.demo.websocket.mapper;

import com.example.demo.websocket.dto.ChatMessageRequest;
import com.example.demo.websocket.entity.ChatMessage;
import org.mapstruct.Mapper;

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
    ChatMessage toEntity(ChatMessageRequest messageRequest);
}
