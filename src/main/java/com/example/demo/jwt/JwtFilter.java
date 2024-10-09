package com.example.demo.jwt;

import com.example.demo.auth.AuthenticateMember;
import com.example.demo.member.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwt;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * packageName    : com.example.demo.jwt
 * fileName       : JwtFilter
 * author         : JAEIK
 * date           : 10/9/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/9/24        JAEIK       최초 생성
 */
@RequiredArgsConstructor
public class JwtFilter implements Filter {
    private final JwtProvider jwtProvider;
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        // HTTP 요청에서 토큰 추출
        String token = resolveToken(httpServletRequest);
        if (token != null && jwtProvider.validateToken(token)) {
            // 유효한 토큰에서 클레임을 파싱
            Jws<Claims> claims = jwtProvider.parseClaims(token);
            if (claims != null) {
                // 클레임에서 사용자 이름 가져옴
                String username = claims.getBody().getSubject();
                // 인증된 사용자 정보를 요청 속성에 설정
                servletRequest.setAttribute(VerifyUserFilter.AUTHENTICATE_USER, username);
            }
        }

        // 인증된 사용자 정보를 가져옵니다.
        Object attribute = servletRequest.getAttribute(VerifyUserFilter.AUTHENTICATE_USER);
        if (attribute instanceof AuthenticateMember authenticateMember) {
            // 인증된 사용자의 이름을 가져옵니다.
            String username = authenticateMember.username();
            // 다음 필터로 요청을 전달
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            //인증되지 않은 경우 400 Bad Request 오류를 응답한다.
            HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
            httpServletResponse.sendError(HttpStatus.BAD_REQUEST.value(),"잘못된 요청입니다.");
        }
    }

    //HTTP 요청에서 JWT 토큰 추출
    private String resolveToken(HttpServletRequest httpServletRequest) {
        String bearerToken = httpServletRequest.getHeader(JwtProperties.ACCESS_TOKEN_HEADER);
        if (bearerToken != null && bearerToken.startsWith(JwtProperties.ACCESS_TOKEN_PREFIX)) {
            return bearerToken.substring(JwtProperties.ACCESS_TOKEN_PREFIX.length());
        }
        return null;
    }
}
