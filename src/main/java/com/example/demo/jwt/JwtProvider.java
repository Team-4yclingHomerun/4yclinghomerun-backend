package com.example.demo.jwt;

import com.example.demo.member.entity.Role;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
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
@Component
public class JwtProvider {
    public static final long ACCESS_TOKEN_TIME = 1000 * 60 * 30; //30분
    public static final String ACCESS_PREFIX_STRING = "Bearer"; // Bearer 방식 지정
    public static final String ACCESS_HEADER_STRING = "Authorization"; //헤더 이름 지정

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
    public JwtToken createJwtToken(UUID id, String username, Role role) {
        String accessToken = createAccessToken(id, username, role);
        return new JwtToken(accessToken);
    }

    // 액세스 토큰 생성 메서드
    public String createAccessToken(UUID id, String username, Role role) {
        Map<String, Object> claims = new HashMap<>();

        claims.put("id", id);
        claims.put("username", username);
        if (role == Role.USER) {
            claims.put("isUser", true);
            claims.put("isAdmin", false);
        }else if (role == Role.ADMIN) {
            claims.put("isUser", false);
            claims.put("isAdmin", true);
        }
        Date expiration = new Date(System.currentTimeMillis() + ACCESS_TOKEN_TIME);

        return ACCESS_TOKEN_TIME + Jwts.builder()
                .subject(id.toString())
                .claims(claims)
                .expiration(expiration)
                .signWith(getSignWith())
                .compact();

 }
 // Jwt 토큰 파싱 메서드
 public Jws<Claims> parseClaims(String token) {
    Jws<Claims> claimsJws;
    try {
        claimsJws = Jwts.parser()
                .verifyWith(getSignWith())
                .build()
                .parseSignedClaims(token);
    }catch (ExpiredJwtException eje) {
        return null;
    }catch (JwtException je) {
        throw new IllegalArgumentException("잘못된 토큰입니다.");
    }
    return claimsJws;
    }
}

