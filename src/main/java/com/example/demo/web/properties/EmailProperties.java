package com.example.demo.web.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;

import java.util.Properties;

/**
 * packageName    : com.example.demo.web.properties
 * fileName       : EmailProperties
 * author         : JAEIK
 * date           : 10/22/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/22/24        JAEIK       최초 생성
 */
@ConfigurationProperties("demo.email")
@ConfigurationPropertiesBinding
public record EmailProperties(
        String host,
        int port,
        String username,
        String password,
        boolean auth,
        boolean starttlsEnable,
        boolean starttlsRequired,
        int connectionTimeout,
        int timeout,
        int writeTimeout

) {
}

