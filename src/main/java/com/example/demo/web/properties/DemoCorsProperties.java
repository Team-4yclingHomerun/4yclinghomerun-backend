package com.example.demo.web.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;

/**
 * packageName    : com.example.demo.web.properties
 * fileName       : DemoCrosProperties
 * author         : JAEIK
 * date           : 10/14/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/14/24        JAEIK       최초 생성
 */
@ConfigurationProperties("demo.cors")
@ConfigurationPropertiesBinding
public record DemoCorsProperties(
        Boolean allowCredentials,
        String[] allowedHeaders,
        String[] allowedOrigins,
        String[] allowedMethods,
        String[] exposeHeaders,
        Long maxAge
) {
}
