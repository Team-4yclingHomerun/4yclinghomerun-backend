package com.example.demo.web.configuration;

import com.example.demo.oauth.OauthServerTypeConverter;
import com.example.demo.web.properties.DemoCorsProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

/**
 * packageName    : com.example.demo.web.configuration
 * fileName       : CorsConfiguration
 * author         : JAEIK
 * date           : 10/14/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/14/24        JAEIK       최초 생성
 */
@Configuration
@RequiredArgsConstructor
@Slf4j
public class CorsConfiguration implements WebMvcConfigurer {

    private final DemoCorsProperties cors;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        log();
        registry.addMapping("/**")
                .allowCredentials(cors.allowCredentials())
                .allowedHeaders(cors.allowedHeaders())
                .allowedOrigins(cors.allowedOrigins())
                .allowedMethods(cors.allowedMethods())
                .exposedHeaders(cors.exposeHeaders())
                .maxAge(cors.maxAge());
    }
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new OauthServerTypeConverter());
    }

    private void log() {
        if (!log.isInfoEnabled()) {
            return;
        }

        log.info("""
                CORS Properties
                allowCredentials: {}
                allowedHeaders: {}
                allowedOrigins: {}
                allowedMethods: {}
                exposeHeaders: {}
                maxAge: {} sec
                """,
                cors.allowCredentials(),
                Arrays.toString(cors.allowedHeaders()),
                Arrays.toString(cors.allowedOrigins()),
                Arrays.toString(cors.allowedMethods()),
                Arrays.toString(cors.exposeHeaders()),
                cors.maxAge());
    }
}
