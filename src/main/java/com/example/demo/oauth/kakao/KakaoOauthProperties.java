package com.example.demo.oauth.kakao;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;

/**
 * packageName    : com.example.demo.oauth.properties
 * fileName       : KakaoOauthProperties
 * author         : JAEIK
 * date           : 10/17/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/17/24        JAEIK       최초 생성
 */
@ConfigurationProperties("demo.oauth")
@ConfigurationPropertiesBinding
public record KakaoOauthProperties(
        String clientId,
        String clientSecret,
        String redirectUri,
        String[] scope
) {
}
