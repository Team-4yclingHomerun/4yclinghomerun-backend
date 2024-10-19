package com.example.demo.oauth.kakao;

import com.example.demo.oauth.OauthMember;
import com.example.demo.oauth.OauthMemberClient;
import com.example.demo.oauth.OauthServerType;
import com.example.demo.oauth.kakao.dto.KakaoMemberResponse;
import com.example.demo.oauth.kakao.dto.KakaoToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * packageName    : com.example.demo.oauth.kakao
 * fileName       : KakaoMemberClient
 * author         : JAEIK
 * date           : 10/18/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/18/24        JAEIK       최초 생성
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class KakaoMemberClient implements OauthMemberClient {

    private final KakaoApiClientImpl kakaoApiClient;
    private final KakaoOauthProperties kakaoOauthProperties;

    @Override
    public OauthServerType supportServer() {
        return OauthServerType.KAKAO;
    }

    @Override
    public OauthMember fetch(String authCode) {
        log.info("Fetching Kakao token with auth code: {}", authCode);
        KakaoToken tokenInfo = kakaoApiClient.fetchToken(tokenRequestParams(authCode));
        if (tokenInfo == null) {
            // 로그 기록 또는 예외 처리
            log.error("Failed to fetch token from Kakao.");
            throw new RuntimeException("Failed to fetch token");
        }
        log.info("Fetched Kakao token: {}", tokenInfo.accessToken());
        KakaoMemberResponse kakaoMemberResponse =
                kakaoApiClient.fetchMember("Bearer " + tokenInfo.accessToken());
        return kakaoMemberResponse.toDomain();
    }

    private MultiValueMap<String, String> tokenRequestParams(String authCode) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", kakaoOauthProperties.clientId());
        params.add("client_secret", kakaoOauthProperties.clientSecret());
        params.add("redirect_uri", kakaoOauthProperties.redirectUri());
        params.add("code", authCode);
        return params;
    }
}

