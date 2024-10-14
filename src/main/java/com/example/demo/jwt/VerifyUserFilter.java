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
                // 로그인 요청 데이터 파싱
                MemberSignInRequest memberSignInRequest = objectMapper.readValue(request.getReader(), MemberSignInRequest.class);
                // 사용자 인증 검증
                MemberVerifyResponse verifyResponse = memberService.verifyUser(memberSignInRequest);
                if (verifyResponse.isValid()) {
                    // 인증 성공 시 사용자 정보를 요청에 설정
                    request.setAttribute(AUTHENTICATE_USER, new AuthenticateMember(
                            verifyResponse.id(),
                            memberSignInRequest.username(),
                            verifyResponse.roles())
                    );
                } else {
                    throw new IllegalArgumentException();
                }
                // 다음 필터로 요청 전달
                filterChain.doFilter(request, response);
            } catch (Exception e) {
                // 예외 발생 시 에러 응답
                HttpServletResponse httpServletResponse = (HttpServletResponse) response;
                httpServletResponse.sendError(HttpStatus.BAD_REQUEST.value());
            }
        }else {
            filterChain.doFilter(request, response);
        }
    }
}


