package com.example.demo.oauth.google;

import com.example.demo.jwt.JwtProperties;
import com.example.demo.oauth.google.dto.GoogleMemberResponse;
import com.example.demo.oauth.google.dto.GoogleToken;
import com.example.demo.oauth.kakao.dto.KakaoMemberResponse;
import com.example.demo.oauth.kakao.dto.KakaoToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

/**
 * packageName    : com.example.demo.oauth.google
 * fileName       : GoogleApiClientService
 * author         : JAEIK
 * date           : 10/20/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/20/24        JAEIK       최초 생성
 */
@Service
public class GoogleApiClientService implements GoogleApiClient{

    private final RestClient restClient = RestClient.create();

    @Override
    public GoogleToken fetchToken(MultiValueMap<String, String> params) {
        return restClient.post()
                .uri("https://oauth2.googleapis.com/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(params)
                .retrieve()
                .body(GoogleToken.class);
    }

    @Override
    public GoogleMemberResponse fetchMember(String bearerToken) {
        return restClient.get()
                .uri("https://accounts.google.com/o/oauth2/v2/auth")
                .header(JwtProperties.ACCESS_TOKEN_HEADER, bearerToken)
                .retrieve()
                .body(GoogleMemberResponse.class);
    }
}
