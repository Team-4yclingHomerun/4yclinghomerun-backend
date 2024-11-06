package com.example.demo.jwt;

import com.example.demo.exception.AuthorizationErrorCode;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

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
}
