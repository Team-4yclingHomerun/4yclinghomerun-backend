package com.example.demo.oauth;

import jakarta.validation.constraints.NotNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * packageName    : com.example.demo.oauth.properties
 * fileName       : OauthServerTypeConverter
 * author         : JAEIK
 * date           : 10/17/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/17/24        JAEIK       최초 생성
 */
@Component
public class OauthServerTypeConverter implements Converter<String, OauthServerType> {
    // Converter 자동으로 타입 변환, 타입 안전성
    @Override
    public OauthServerType convert(@NotNull String source) {
        return OauthServerType.fromName(source);
    }
}
