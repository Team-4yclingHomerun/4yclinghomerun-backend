package com.example.demo.oauth.google;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * packageName    : com.example.demo.oauth.google
 * fileName       : GoogleToken
 * author         : JAEIK
 * date           : 10/20/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/20/24        JAEIK       최초 생성
 */
@JsonNaming(value = SnakeCaseStrategy.class)
public record GoogleToken(
        String accessToken,
        Integer expiresIn,
        String refreshToken,
        String scope,
        String tokenType
) {
}
