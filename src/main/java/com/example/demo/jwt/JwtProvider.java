package com.example.demo.jwt;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

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
public class JwtProvider {
    private final SecretKey secretKey;
    private final long maxAge;

    // Jwt 생성하고 검증하는데 필요한 비밀키를 설정
    public JwtProvider(String key, long maxAge) {
        String keyBase64Encoded = Base64.getEncoder().withoutPadding().encodeToString(key.getBytes());
        secretKey = Keys.hmacShaKeyFor(keyBase64Encoded.getBytes());
        this.maxAge = maxAge;
    }

    // 액세스 토큰 생성 메서드
    public String create(String subject, Map<String, ?> claimMap) {
        Date issuedAt = new Date();
        Date expiration = new Date(System.currentTimeMillis() + maxAge);

        return Jwts.builder()
                .subject(subject)
                .claims(claimMap)
                .issuedAt(issuedAt)
                .expiration(expiration) //유효 기간
                .signWith(secretKey) // 서명 설정
                .compact();
    }
}


