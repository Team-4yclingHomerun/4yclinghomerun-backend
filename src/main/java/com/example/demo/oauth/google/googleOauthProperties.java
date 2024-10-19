package com.example.demo.oauth.google;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;

/**
 * packageName    : com.example.demo.oauth.google
 * fileName       : googleOauthProperties
 * author         : JAEIK
 * date           : 10/20/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/20/24        JAEIK       최초 생성
 */
@ConfigurationProperties("demo.oauth.google")
@ConfigurationPropertiesBinding
public record googleOauthProperties(
        String clientId,
        String clientSecret,
        String redirectUri,
        String[] scope
) {
}
