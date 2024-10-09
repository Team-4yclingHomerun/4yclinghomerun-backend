package com.example.demo.jwt;

import com.example.demo.auth.AuthenticateMember;
import com.example.demo.member.dto.MemberSignInRequest;
import com.example.demo.member.dto.MemberVerifyResponse;
import com.example.demo.member.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.http.HttpRequest;

/**
 * packageName    : com.example.demo.jwt
 * fileName       : VerifyUserFilter
 * author         : JAEIK
 * date           : 10/9/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/9/24        JAEIK       최초 생성
 */
@Component
@RequiredArgsConstructor
public class VerifyUserFilter implements Filter {
    public static final String AUTHENTICATE_USER = "authenticateUser";
    private final ObjectMapper objectMapper;

    private final MemberService memberService;


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String requestURI = httpServletRequest.getRequestURI();
        if ((httpServletRequest.getMethod().equals("POST") && requestURI.equals("/yclinghomerun/signIn") )) {
            try {
                MemberSignInRequest memberSignInRequest = objectMapper.readValue(request.getReader(), MemberSignInRequest.class);
                MemberVerifyResponse verifyResponse = memberService.verifyUser(memberSignInRequest);
                if (verifyResponse.isValid()) {
                    request.setAttribute(AUTHENTICATE_USER, new AuthenticateMember(memberSignInRequest.username()));
                } else {
                    throw new IllegalArgumentException();
                }
                filterChain.doFilter(request, response);
            } catch (Exception e) {
                HttpServletResponse httpServletResponse = (HttpServletResponse) response;
                httpServletResponse.sendError(HttpStatus.BAD_REQUEST.value());
            }
        }else {
            filterChain.doFilter(request, response);
        }
    }
}


