package com.example.demo.oauth.google;

import com.example.demo.oauth.OauthMember;
import com.example.demo.oauth.OauthMemberClient;
import com.example.demo.oauth.OauthServerType;
import com.example.demo.oauth.google.dto.GoogleMemberResponse;
import com.example.demo.oauth.google.dto.GoogleToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * packageName    : com.example.demo.oauth.google
 * fileName       : GoogleMemberClient
 * author         : JAEIK
 * date           : 10/20/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/20/24        JAEIK       최초 생성
 */
@Component
@RequiredArgsConstructor
public class GoogleMemberClient implements OauthMemberClient {

    private final GoogleApiClientService googleApiClient;
    private final GoogleOauthProperties googleOauthProperties;

    @Override
    public OauthServerType supportServer() {
        return OauthServerType.GOOGLE;
    }

    @Override
    public OauthMember fetch(String authCode) {
        // 토큰 요청
        GoogleToken tokenInfo = googleApiClient.fetchToken(tokenRequestParams(authCode));
        // 액세스 토큰으로 사용자 정보 요청
        GoogleMemberResponse googleMemberResponse = googleApiClient.fetchMember("bearerToken " + tokenInfo.accessToken());
        // 응답을 객체로 반환
        return googleMemberResponse.toDomain();
    }

    private MultiValueMap<String, String> tokenRequestParams(String authCode) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", googleOauthProperties.clientId());
        params.add("client_secret", googleOauthProperties.clientSecret());
        params.add("code", authCode);
        return params;
    }
}
