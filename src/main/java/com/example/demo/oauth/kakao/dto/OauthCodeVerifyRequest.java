package com.example.demo.oauth.kakao.dto;

/**
 * packageName    : com.example.demo.oauth.kakao.dto
 * fileName       : OauthCodeVerifyRequest
 * author         : JAEIK
 * date           : 10/30/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/30/24       JAEIK       최초 생성
 */
public record OauthCodeVerifyRequest(
        String code
) {
}
