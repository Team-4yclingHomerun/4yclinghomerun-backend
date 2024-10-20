package com.example.demo.config;

import com.example.demo.jwt.VerifyUserFilter;
import com.example.demo.member.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * packageName    : com.example.demo.config
 * fileName       : WebConfig
 * author         : JAEIK
 * date           : 10/17/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/17/24        JAEIK       최초 생성
 */
@Configuration
public class WebConfig {
    @Bean
    public FilterRegistrationBean verifyUserFilter(ObjectMapper mapper, MemberService memberService) {
        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(new VerifyUserFilter(mapper, memberService));
        filterFilterRegistrationBean.setOrder(1);
        filterFilterRegistrationBean.addUrlPatterns("/yclinghomerun/login", "/yclinghomerun/signUp");
        return filterFilterRegistrationBean;
    }
//    @Bean
//    public FilterRegistrationBean jwtFilter(JwtParser parser, JwtProvider jwtProvider) {
//        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
//        filterFilterRegistrationBean.setFilter((Filter) new JwtFilter(parser, jwtProvider));
//        filterFilterRegistrationBean.setOrder(2);
//        filterFilterRegistrationBean.addUrlPatterns("/yclinghomerun/**");
//        return filterFilterRegistrationBean;
//    @Bean FilterRegistrationBean oauthFilter() {
//        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
//        filterRegistrationBean.setFilter(new OAuth2LoginAuthenticationFilter());
//        filterRegistrationBean.setOrder(2);
//        filterRegistrationBean.addUrlPatterns("/oauth/*");
//        return filterRegistrationBean;
//    }
}
