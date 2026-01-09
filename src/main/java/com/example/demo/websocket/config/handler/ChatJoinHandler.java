package com.example.demo.websocket.config.handler;

import com.example.demo.auth.AuthenticateMember;
import com.example.demo.exception.SignInErrorCode;
import com.example.demo.member.entity.Member;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.oauth.common.entity.OauthMember;
import com.example.demo.oauth.common.repository.OauthMemberRepository;
import com.example.demo.websocket.service.ChatEventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

import java.util.Map;

/**
 * packageName    : com.example.demo.websocket.config.handler
 * fileName       : JoinHandler
 * author         : JAEIK
 * date           : 1/3/26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 1/3/26        JAEIK       최초 생성
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ChatJoinHandler implements ApplicationListener<SessionSubscribeEvent> {
    private final MemberRepository memberRepository;
    private final OauthMemberRepository oauthMemberRepository;
    private final ChatEventService chatEventService;

    @Override
    public void onApplicationEvent(SessionSubscribeEvent event) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());

        // SessionSubscribeEvent 모든 SUBSCRIBE 대해 발생하므로 채팅방 구독만 처리
        if (!"/topic/chat/room".equals(accessor.getDestination())) {
            return;
        }

        Map<String, Object> sessionAttributes = accessor.getSessionAttributes();
        if (sessionAttributes == null) {
            return;
        }

        AuthenticateMember auth = (AuthenticateMember) sessionAttributes.get("Auth");
        if (auth == null) return;

        String nickname = memberRepository.findByUsername(auth.username())
                .map(Member::getNickname)
                .orElseGet(() -> oauthMemberRepository.findById(auth.id())
                        .map(OauthMember::getNickname)
                        .orElseThrow(SignInErrorCode.NOT_FOUND_USERNAME::defaultException));

        sessionAttributes.put("sender", nickname);
        chatEventService.join(nickname, accessor.getSessionId());

        log.info("채팅방 join 시도: nickname={}, session={}",
                nickname, accessor.getSessionId());

    }
}

