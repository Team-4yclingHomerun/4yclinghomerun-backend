package com.example.demo.websocket;

import com.example.demo.auth.AuthenticateMember;
import com.example.demo.exception.SignInErrorCode;
import com.example.demo.jwt.JwtParser;
import com.example.demo.jwt.JwtProperties;
import com.example.demo.member.entity.Member;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.oauth.common.entity.OauthMember;
import com.example.demo.oauth.common.repository.OauthMemberRepository;
import com.example.demo.websocket.dto.ChatMessageRequest;
import com.example.demo.websocket.dto.MessageType;
import com.example.demo.websocket.entity.ChatMessage;
import com.example.demo.websocket.mapper.ChatMessageDtoMapper;
import com.example.demo.websocket.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import java.util.Optional;


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
    private final OauthMemberRepository oauthMemberRepository;
    private final MemberRepository memberRepository;
    private final JwtParser jwtParser;

    @MessageMapping("/chat/message")
    public void message(ChatMessageRequest message, SimpMessageHeaderAccessor headerAccessor) {
        log.debug("message:{}", message);
        String token = headerAccessor.getFirstNativeHeader(JwtProperties.ACCESS_TOKEN_HEADER);

        if (token != null && token.startsWith(JwtProperties.ACCESS_TOKEN_PREFIX)) {
            token = token.substring(JwtProperties.ACCESS_TOKEN_PREFIX.length());
        }

        AuthenticateMember authenticateMember = jwtParser.getAuthenticateMember(token);

        Optional<Member> member = memberRepository.findByUsername(authenticateMember.username());

        Optional<OauthMember> oauthMember = oauthMemberRepository.findById(authenticateMember.id());

        String nickname = member
                .map(Member::getNickname)
                .orElseGet(() -> oauthMember
                        .map(OauthMember::getNickname)
                        .orElseThrow(SignInErrorCode.NOT_FOUND_USERNAME::defaultException));

        message.setSender(nickname);

        if (MessageType.JOIN.equals(message.getType())) {
            message.setMessage(nickname + "님이 입장하셨습니다.");
        }

        ChatMessage chatMessage = chatMessageDtoMapper.convertToEntity(message);
        chatMessageRepository.save(chatMessage);

        // @SendTo 없이 메서드 내에서 직접 전송
        messageSendingOperations.convertAndSend("/topic/chat/room", message);
        log.debug("send message /topic/chat/room: {}", message);
    }
}
