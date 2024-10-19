package com.example.demo.oauth.kakao;

import com.example.demo.jwt.JwtProperties;
import com.example.demo.oauth.kakao.dto.KakaoMemberResponse;
import com.example.demo.oauth.kakao.dto.KakaoToken;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

/**
 * packageName    : com.example.demo.oauth.kakao
 * fileName       : KakaoApiClientImpl
 * author         : JAEIK
 * date           : 10/19/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/19/24        JAEIK       최초 생성
 */
@Service
public class KakaoApiClientImpl implements KakaoApiClient{

    @Override
    public KakaoToken fetchToken(MultiValueMap<String, String> params) {
        RestClient restClient = RestClient.create();
        return restClient.post()
                .uri("https://kauth.kakao.com/oauth/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(params)
                .retrieve()
                .body(KakaoToken.class);
    }

    @Override
    public KakaoMemberResponse fetchMember(String bearerToken) {
        RestClient restClient = RestClient.create();
        return restClient.get()
                .uri("https://kapi.kakao.com/v2/user/me")
                .header(JwtProperties.ACCESS_TOKEN_HEADER, bearerToken)
                .retrieve()
                .body(KakaoMemberResponse.class);
    }
}
