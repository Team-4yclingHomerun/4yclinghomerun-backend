package com.example.demo.member.service;

import com.example.demo.jwt.JwtProvider;
import com.example.demo.jwt.JwtToken;
import com.example.demo.member.entity.Role;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * packageName    : com.example.demo.member.service
 * fileName       : JwtAuthenticationProxyService
 * author         : JAEIK
 * date           : 10/13/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/13/24        JAEIK       최초 생성
 */
@Service
public class JwtAuthenticationProxyService {
    private final JwtProvider jwtProvider;

    public JwtAuthenticationProxyService(
            @Value("${jwt.secret-key}") String keyParam,
            @Value("${jwt.max-age}") Long maxAge
    ) {
        if (maxAge == null) {
            maxAge = 3_600_000L;
        }

        this.jwtProvider = new JwtProvider(keyParam, maxAge);
    }

    public JwtToken createJwtToken(UUID id, String username, Role role) {
        String accessToken = createAccessToken(id, username, role);
        return new JwtToken(accessToken);
    }

    private String createAccessToken(UUID id, String username, Role role) {
        // preconditions: nonnull, ... (throw error/exception, assert, ...)
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", id.toString());
        claims.put("role", role.toString()); // enum 열거형 객체타입이니 문자열 변환

//        if (role == Role.USER) {
//            claims.put("isUser", true);
//            claims.put("isAdmin", false);
//        }else if (role == Role.ADMIN) {
//            claims.put("isUser", false);
//            claims.put("isAdmin", true);
//        }
        // refactor:
//        claims.put("isUser", role == Role.USER);
//        claims.put("isAdmin", role == Role.ADMIN);

        return jwtProvider.create(username, claims);
    }
}
