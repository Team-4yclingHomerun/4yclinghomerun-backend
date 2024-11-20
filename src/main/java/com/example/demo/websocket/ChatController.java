package com.example.demo.websocket;

import com.example.demo.auth.AuthenticateMember;
import com.example.demo.auth.AuthorizationConstants;
import com.example.demo.exception.ChatErrorCode;
import com.example.demo.exception.CustomException;
import com.example.demo.jwt.JwtAuthorizationFilter;
import com.example.demo.jwt.JwtParser;
import com.example.demo.jwt.JwtProperties;
import com.example.demo.jwt.JwtProvider;
import com.example.demo.websocket.dto.ChatMessageRequest;
import com.example.demo.websocket.dto.MessageType;
import com.example.demo.websocket.entity.ChatMessage;
import com.example.demo.websocket.mapper.ChatMessageDtoMapper;
import com.example.demo.websocket.repository.ChatMessageRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;


/**
 * packageName    : com.example.demo.websocket
 * fileName       : ChatController
 * author         : JAEIK
 * date           : 10/29/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/29/24        JAEIK       최초 생성
 */
@RequiredArgsConstructor
@Controller
@Slf4j
public class ChatController {

    private final SimpMessageSendingOperations messageSendingOperations;
    private final ChatMessageRepository chatMessageRepository;
    private final ChatMessageDtoMapper chatMessageDtoMapper;

    @MessageMapping("/chat/message")
    public void message(ChatMessageRequest message, HttpServletRequest request) {
        log.debug("message:{}", message);

        AuthenticateMember authenticateMember = (AuthenticateMember) request.getAttribute(AuthorizationConstants.LOGIN_MEMBER_ATTRIBUTE);
        log.debug("authenticateMember:{}", authenticateMember);

        if (authenticateMember == null) {
            throw ChatErrorCode.NOT_FOUND_USER.defaultException();
        }

        if (MessageType.JOIN.equals(message.getType()))
            message.setMessage(message.getSender() + "님이 입장하셨습니다.");

        ChatMessage chatMessage = chatMessageDtoMapper.toEntity(message);
        chatMessageRepository.save(chatMessage);

        // @SendTo 없이 메서드 내에서 직접 전송
        messageSendingOperations.convertAndSend("/topic/chat/room", message);
        log.debug("send message /topic/chat/room: {}", message);
    }
}
