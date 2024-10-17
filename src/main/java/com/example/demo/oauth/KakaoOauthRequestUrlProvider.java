package com.example.demo.oauth;

import com.example.demo.oauth.authcode.AuthCodeRequestUrlProvider;
import com.example.demo.web.properties.KakaoOauthProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * packageName    : com.example.demo.oauth
 * fileName       : KakaoOauthUrlProvider
 * author         : JAEIK
 * date           : 10/17/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/17/24        JAEIK       최초 생성
 */
@RequiredArgsConstructor
@Slf4j
@Component
public class KakaoOauthRequestUrlProvider implements AuthCodeRequestUrlProvider {

    private final KakaoOauthProperties kakaoOauthProperties;

    @Override
    public OauthServerType supportServer() {
        log.info("Returning server type: {}", OauthServerType.KAKAO);
        return OauthServerType.KAKAO;
    }
    @Override
    public String provide() {
        String uri = UriComponentsBuilder  // URI 구성하는데 특화된 빌더
                .fromUriString("https://kauth.kakao.com/oauth/authorize")
                .queryParam("response_type", "code")
                .queryParam("client_id", kakaoOauthProperties.clientId())
                .queryParam("redirect_uri", kakaoOauthProperties.redirectUri())
                .queryParam("scope", String.join(",", kakaoOauthProperties.scope()))
                .toUriString();
        System.out.println("Generated URL: " + uri);
        return uri;
    }
}
