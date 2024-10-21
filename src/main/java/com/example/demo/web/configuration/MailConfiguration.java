package com.example.demo.web.configuration;

import com.example.demo.web.properties.EmailProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * packageName    : com.example.demo.email
 * fileName       : MailConfig
 * author         : JAEIK
 * date           : 10/22/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/22/24        JAEIK       최초 생성
 */
@Configuration
@RequiredArgsConstructor
public class MailConfiguration {
    private final EmailProperties emailProperties;

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(emailProperties.host());
        javaMailSender.setUsername(emailProperties.username());
        javaMailSender.setPassword(emailProperties.password());
        javaMailSender.setPort(emailProperties.port());
        javaMailSender.setJavaMailProperties(getMailProperties());
        return javaMailSender;
    }
    private Properties getMailProperties(){
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", emailProperties.auth());
        properties.put("mail.smtp.starttls.enable", emailProperties.starttlsEnable());
        properties.put("mail.smtp.starttls.required", emailProperties.starttlsRequired());
        properties.put("mail.smtp.connectiontimeout", emailProperties.connectionTimeout());
        properties.put("mail.smtp.timeout", emailProperties.timeout());
        properties.put("mail.smtp.writetimeout", emailProperties.writeTimeout());
        return properties;
    }
}
