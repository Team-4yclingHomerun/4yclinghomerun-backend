package com.example.demo.jpa.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * packageName    : com.example.demo.jpa.config
 * fileName       : DemoJpaConfiguration
 * author         : JAEIK
 * date           : 10/2/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/2/24        JAEIK       최초 생성
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.example.demo.member.repository")
@EntityScan(basePackages = "com.example.demo.member.entity")
public class DemoJpaConfiguration {
}
