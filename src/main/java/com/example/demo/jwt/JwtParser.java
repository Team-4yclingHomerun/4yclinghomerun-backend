package com.example.demo.jwt;

import com.example.demo.exception.AuthorizationErrorCode;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
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
}
