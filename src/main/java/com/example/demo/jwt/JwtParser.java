package com.example.demo.jwt;

import com.example.demo.auth.AuthenticateMember;
import com.example.demo.exception.AuthorizationErrorCode;
import com.example.demo.member.entity.Role;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.*;

/**
 * packageName    : com.example.demo.jwt
 * fileName       : JwtParser
 * author         : JAEIK
 * date           : 10/13/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/13/24        JAEIK       최초 생성
 */
@Slf4j
@Component
public class JwtParser {
    private final SecretKey secretKey;

    public JwtParser(@Value("${jwt.secret-key}") String key) {
        String keyBase64Encoded = Base64.getEncoder().withoutPadding().encodeToString(key.getBytes());
        secretKey = Keys.hmacShaKeyFor(keyBase64Encoded.getBytes());
    }

    // Jwt 토큰 파싱 메서드
    public Map<String, Object> parseClaims(String token) {
        // skip validate token, ...
        Jws<Claims> claimsJws;
        try {
            claimsJws = Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token);
        } catch (ExpiredJwtException eje) {
            return null;
        } catch (JwtException je) {
            var INVALID_JWT = AuthorizationErrorCode.INVALID_JWT;
            throw INVALID_JWT.defaultException(je);
        }
        return Map.copyOf(claimsJws.getPayload());
    }

    // Jwt 토큰 검증 메소드
    public boolean validateToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            return !claims.getExpiration().before(new Date());
        } catch (SecurityException | MalformedJwtException e) {
            log.error("잘못된 JWT 서명입니다");
        } catch (ExpiredJwtException e) {
            log.error("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            log.error("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            log.error("JWT 토큰이 잘못되었습니다.");

        }
        return false;
    }

    /* Jwt 토큰에서 사용자 인증 정보를 가져오는 메서드
      - 토큰 정보 object 타입이 일치하는지 확인한다 일치하지않으면 예외
    */
    public AuthenticateMember getAuthenticateMember(String token) {
        if (!validateToken(token)) {
            throw new IllegalArgumentException("유효하지 않은 JWT 토큰 입니다.");
        }

        Map<String, Object> claims = parseClaims(token);
        log.debug("Parsed claims: {}", claims);

        if (!(claims.get("id") instanceof String uuidString)) {
            throw new IllegalArgumentException("'id'는 String 타입이어야 합니다.");
        }
        if (!(claims.get("sub") instanceof String username)) {
            throw new IllegalArgumentException("'sub'은 String 타입이어야 합니다.");
        }
        if (!(claims.get("role") instanceof String roleString)) { // "USER"
            throw new IllegalArgumentException("'role'은 String 타입이어야 합니다.");
        }

        UUID id = UUID.fromString(uuidString);
        Role role = Role.valueOf(roleString);

        return new AuthenticateMember(id, username, Set.of(role));
    }

    public String extractToken(HttpServletRequest request) {
        String header = request.getHeader(JwtProperties.ACCESS_TOKEN_HEADER);
//        if (header == null) {
//            throw new IllegalArgumentException("Authorization 헤더가 비어있습니다.");
//        }
        // 비로그인도 (이 프로젝트에서는 화이트에러로 관리하고있지만  채팅방메시지를 비로그인자도 볼 수 있게하기 위해)
        if (header == null || header.isBlank()) {
            return null;
        }
        if (!header.startsWith(JwtProperties.ACCESS_TOKEN_PREFIX)) {
            throw new IllegalArgumentException("Authorization 헤더의 형식이 잘못되었습니다");
        }

        return header.substring(JwtProperties.ACCESS_TOKEN_PREFIX.length());
    }
}
