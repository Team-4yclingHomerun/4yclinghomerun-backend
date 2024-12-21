package com.example.demo.websocket;

import com.example.demo.websocket.mapper.ChatMessageDtoMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;

/**
 * packageName    : com.example.demo.websocket
 * fileName       : TestConfiguration
 * author         : JAEIK
 * date           : 12/19/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 12/19/24       JAEIK       최초 생성
 */
@org.springframework.boot.test.context.TestConfiguration
public class TestConfiguration {
    @Bean
    public ChatMessageDtoMapper chatMessageDtoMapper() {
        return Mappers.getMapper(ChatMessageDtoMapper.class);
    }
}
