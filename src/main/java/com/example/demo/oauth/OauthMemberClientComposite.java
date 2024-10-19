package com.example.demo.oauth;

import com.example.demo.oauth.kakao.KakaoMemberClient;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * packageName    : com.example.demo.oauth
 * fileName       : OauthMemberClientCompsoite
 * author         : JAEIK
 * date           : 10/18/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/18/24        JAEIK       최초 생성
 */
@Component
public class OauthMemberClientComposite {
    private final Map<OauthServerType, OauthMemberClient> mapping;

    public OauthMemberClientComposite(Set<OauthMemberClient> clients) {
        mapping = clients.stream()
                .collect(Collectors.toMap(
                        OauthMemberClient::supportServer,
                        Function.identity()
                ));
    }

    public OauthMember fetch(OauthServerType oauthServerType, String authCode) {
      //   kakaoMemberClient.fetch();  인터페이스로 구현했으니
        return getClient(oauthServerType).fetch(authCode);
    }

    private OauthMemberClient getClient(OauthServerType oauthServerType) {
        return Optional.ofNullable(mapping.get(oauthServerType))
                .orElseThrow(()->new RuntimeException("지원하지 않는 소설 로그인 타입입니다."));
    }
}
