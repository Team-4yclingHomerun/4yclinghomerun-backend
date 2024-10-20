package com.example.demo.oauth.kakao;

import com.example.demo.jwt.JwtProperties;
import com.example.demo.oauth.kakao.dto.KakaoMemberResponse;
import com.example.demo.oauth.kakao.dto.KakaoToken;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * packageName    : com.example.demo.oauth.kakao
 * fileName       : KakaoApiClient
 * author         : JAEIK
 * date           : 10/18/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/18/24        JAEIK       최초 생성
 */
public interface KakaoApiClient {
    // 서버에 Post 요청 보내는 어노테이션
    KakaoToken fetchToken(@RequestParam MultiValueMap<String, String> params);

    KakaoMemberResponse fetchMember(@RequestHeader(name = JwtProperties.ACCESS_TOKEN_HEADER) String bearerToken);
}


