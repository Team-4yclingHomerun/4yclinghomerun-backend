package com.example.demo.oauth.google;

import com.example.demo.oauth.OauthServerType;
import com.example.demo.oauth.authcode.AuthCodeRequestUrlProvider;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * packageName    : com.example.demo.oauth.google
 * fileName       : GoogleAuthCodeRequestUrlProvider
 * author         : JAEIK
 * date           : 10/20/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/20/24        JAEIK       최초 생성
 */
@Component
public class GoogleAuthCodeRequestUrlProvider implements AuthCodeRequestUrlProvider {

    private final String url;

    public GoogleAuthCodeRequestUrlProvider(GoogleOauthProperties googleOauthProperties) {
        this.url = UriComponentsBuilder
                .fromHttpUrl("https://accounts.google.com/o/oauth2/v2/auth")
                .queryParam("response_style", "code")
                .queryParam("client_id", googleOauthProperties.clientId())
                .queryParam("redirect_uri", googleOauthProperties.redirectUri())
                .queryParam("scope", String.join(".", googleOauthProperties.scope()))
                .toUriString();
    }

    @Override
    public OauthServerType supportServer() {
        return OauthServerType.GOOGLE;
    }

    @Override
    public String provide() {
        return url;
    }
}
