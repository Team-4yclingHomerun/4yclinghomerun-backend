package com.example.demo.websocket.repository;

import org.springframework.stereotype.Component;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * packageName    : com.example.demo.websocket.repository
 * fileName       : ChatSessionRegistry
 * author         : JAEIK
 * date           : 1/5/26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 1/5/26        JAEIK       최초 생성
 */
@Component
public class ChatSessionRegistry {
    // 웹소켓의 접속한 session 세션 확인/관리 서버 메모리
    // 정적으로 공유하기때문에 멀티스레드 환경에서 안전한 ConcurrentHashMap 사용
    private final ConcurrentHashMap<String, Set<String>> session = new ConcurrentHashMap<>();

    public boolean add(String nickname, String sessionId) {

        boolean isFist = !session.containsKey(nickname);
        session.computeIfAbsent(nickname, k -> ConcurrentHashMap.newKeySet()) // 키가 Map 없으면 새 값을 만들어 넣고 반환, 있으면 기존 값 반환
                .add(sessionId);
        return isFist;

    }

    public boolean remove(String nickname, String sessionId) {
        // key 하나에 value 1개 이상일 수 있으니 Set 자료구조
        Set<String> set = session.get(nickname);
        if (set == null) {
            return false;
        }

        set.remove(sessionId);

        if (set.isEmpty()) {
            session.remove(nickname);
            return true;
        }
        return false;
    }

    public int userCount() {
        return session.size();
    }
}
