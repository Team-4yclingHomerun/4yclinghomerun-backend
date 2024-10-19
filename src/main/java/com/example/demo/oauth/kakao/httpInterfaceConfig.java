package com.example.demo.oauth.kakao;

import com.example.demo.oauth.google.GoogleApiClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

/**
 * packageName    : com.example.demo.oauth.kakao
 * fileName       : HttpInrerfaceConfig
 * author         : JAEIK
 * date           : 10/18/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/18/24        JAEIK       최초 생성
 */
@Configuration
public class httpInterfaceConfig {

    @Bean
    public KakaoApiClient kakaoApiClient() {
        return createApiClient(KakaoApiClientService.class);
    }

    @Bean
    public GoogleApiClient googleApiClient() {
        return createApiClient(GoogleApiClient.class);
    }

    private <T> T createApiClient(Class<T> clientClass) {
        RestClient restClient = RestClient.create();
        RestClientAdapter restClientAdapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory
                .builderFor(restClientAdapter)
                .build();
        return factory.createClient(clientClass);
    }
}
