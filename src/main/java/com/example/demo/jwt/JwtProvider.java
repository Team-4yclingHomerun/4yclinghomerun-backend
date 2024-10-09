package com.example.demo.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.*;

/**
 * packageName    : com.example.demo.jwt
 * fileName       : JwtUtil
 * author         : JAEIK
 * date           : 10/8/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/8/24        JAEIK       최초 생성
 */
@Slf4j
@Component
public class JwtProvider {

    private static String key;
    private static String keyBase64Encoded;
    private static SecretKey signWith;

    // Jwt 생성하고 검증하는데 필요한 비밀키를 설정
    public JwtProvider(@Value("${jwt.secret_key}") String keyParam) {
        key = keyParam;
        keyBase64Encoded = Base64.getEncoder().encodeToString(key.getBytes());
        signWith = Keys.hmacShaKeyFor(keyBase64Encoded.getBytes());

    }

    public static String getKey() {
        return key;
    }

    public static SecretKey getSignWith() {
        return signWith;
    }

    //JWT 토큰을 캡슐화 생성 메서드
    public JwtToken createJwtToken(String username) {
        String accessToken = createAccessToken(username);
        return new JwtToken(accessToken);
    }

    // 액세스 토큰 생성 메서드
    public String createAccessToken(String username) {
        Map<String, Object> claims = new HashMap<>();

        claims.put("username", username);
//        if (role == Role.USER) {
//            claims.put("isUser", true);
//            claims.put("isAdmin", false);
//        }else if (role == Role.ADMIN) {
//            claims.put("isUser", false);
//            claims.put("isAdmin", true);
//        }
        Date expiration = new Date(System.currentTimeMillis() + JwtProperties.ACCESS_TOKEN_EXPIRATION_TIME);

        return JwtProperties.ACCESS_TOKEN_EXPIRATION_TIME + Jwts.builder()
                .claims(claims)
                .expiration(expiration)
                .signWith(getSignWith())
                .compact();
    }

    // Jwt 토큰 검증 메소드
    public boolean validateToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .verifyWith((SecretKey) signWith)
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
    // Jwt 토큰 파싱 메서드
    public Jws<Claims> parseClaims(String token) {
        Jws<Claims> claimsJws;
        try {
            claimsJws = Jwts.parser()
                    .verifyWith(getSignWith())
                    .build()
                    .parseSignedClaims(token);
        } catch (ExpiredJwtException eje) {
            return null;
        } catch (JwtException je) {
            throw new IllegalArgumentException("잘못된 토큰입니다.");
        }
        return claimsJws;
    }
}


