package com.example.demo.oauth;

import java.util.Locale;

import static java.util.Locale.ENGLISH;

/**
 * packageName    : com.example.demo.oauth
 * fileName       : OauthServerType
 * author         : JAEIK
 * date           : 10/17/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/17/24        JAEIK       최초 생성
 */
public enum OauthServerType {
    KAKAO,
    GOOGLE;
    public static OauthServerType fromName(String type) {
        return OauthServerType.valueOf(type.toUpperCase(ENGLISH));
    }
}
