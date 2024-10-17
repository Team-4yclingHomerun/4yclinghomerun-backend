package com.example.demo.oauth;

import com.example.demo.oauth.authcode.AuthCodeRequestUrlProvider;
import com.example.demo.oauth.authcode.AuthCodeRequestUrlProviderComposite;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * packageName    : com.example.demo.oauth
 * fileName       : OauthService
 * author         : JAEIK
 * date           : 10/17/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/17/24        JAEIK       최초 생성
 */
@Service
@RequiredArgsConstructor
public class OauthService {

    private final AuthCodeRequestUrlProviderComposite authCodeRequestUrlProviderComposite;

//    public OauthServcie() {
//        Set<AuthCodeRequestUrlProvider> providers = Set.of(
//                new KakaoOauthRequestUrlProvider()
//        );
//        this.authCodeRequestUrlProviderComposite = new AuthCodeRequestUrlProviderComposite(providers);
//    }

    public String getAuthCodeRequestUrl(OauthServerType oauthServerType) {
        return authCodeRequestUrlProviderComposite.provide(oauthServerType);
    }
}
