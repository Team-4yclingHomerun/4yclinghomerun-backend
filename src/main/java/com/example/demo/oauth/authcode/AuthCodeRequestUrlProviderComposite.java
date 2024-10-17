package com.example.demo.oauth.authcode;


import com.example.demo.oauth.OauthServerType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.toMap;

/**
 * packageName    : com.example.demo.oauth
 * fileName       : AuthCodeRequestUrlProviderComposite
 * author         : JAEIK
 * date           : 10/17/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/17/24        JAEIK       최초 생성
 */
@Component
@Slf4j
public class AuthCodeRequestUrlProviderComposite {

    private final Map<OauthServerType, AuthCodeRequestUrlProvider> mapping;

    public AuthCodeRequestUrlProviderComposite(Set<AuthCodeRequestUrlProvider> providers) {

        mapping = providers.stream()
                .collect(Collectors.toMap(
                        AuthCodeRequestUrlProvider::supportServer,
                        Function.identity()
                ));
        log.info("Providers received: {}", providers);

    }

    public String provide(OauthServerType oauthServerType) {
        return getProvider(oauthServerType).provide();
    }
    private AuthCodeRequestUrlProvider getProvider(OauthServerType oauthServerType) {
        System.out.println("provide kakao:" + mapping.containsKey(oauthServerType));
        return Optional.ofNullable(mapping.get(oauthServerType))
                .orElseThrow(()-> new RuntimeException("지원하지 않는 소셜 로그인 타입입니다."));

    }
}
