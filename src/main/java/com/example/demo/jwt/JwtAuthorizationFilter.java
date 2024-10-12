package com.example.demo.jwt;

import com.example.demo.auth.AuthenticateMember;
import com.example.demo.member.entity.Role;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.security.SignatureException;
import java.util.UUID;

/**
 * packageName    : com.example.demo.jwt
 * fileName       : JwtAuthorizationFilter
 * author         : JAEIK
 * date           : 10/10/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/10/24        JAEIK       최초 생성
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class JwtAuthorizationFilter  {
//    // 인증 없이 접근 가능한 URI
//    private final String[] whiteListUris = new String[]
//            {"/signUp", "/login"};
//    private final JwtProvider jwtProvider;
//    // JSON 변환을 위한 인스턴스
//    private final ObjectMapper objectMapper;
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
//        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
//        // 요청 URI 화이트리스트에 있는지 확인
//        if (whiteListCheck(httpServletRequest.getRequestURI())) {
//            filterChain.doFilter(servletRequest, servletResponse);
//        }
//        // 요청 토큰이 포함되어 있는지 확인
//        if (!isContainToken(httpServletRequest)) {
//            httpServletResponse.sendError(HttpStatus.UNAUTHORIZED.value(), "인증 오류");
//        }
//        try {
//            // JWT 토큰 추출
//            String token = getToken(httpServletRequest);
//            // JWT 기반으로 사용자 인증정보 추출
//            AuthenticateMember authenticateMember = getAuthenticateMember(token);
//            // 사용자의 권환 확인
//            verifyAuthorization(httpServletRequest.getRequestURI(), authenticateMember);
//            log.info("값 : {}", authenticateMember.username());
//            filterChain.doFilter(servletRequest, servletResponse);
//        } catch (JsonParseException e) {
//            log.error("Json 파싱 예외 발생");
//            httpServletResponse.sendError(HttpStatus.BAD_REQUEST.value());
//        } catch (MalformedJwtException | UnsupportedJwtException e) {
//            log.error("Jwt 예외 발생");
//            httpServletResponse.sendError(HttpStatus.UNAUTHORIZED.value(), "인증 오류");
//        } catch (ExpiredJwtException e) {
//            log.error("Jwt 토큰이 만료되었습니다.");
//            httpServletResponse.sendError(HttpStatus.FORBIDDEN.value(), "토큰이 만료 되었습니다.");
//        } catch (AccessDeniedException e) {
//            log.error("권환 예외 발생");
//            httpServletResponse.sendError(HttpStatus.UNAUTHORIZED.value(),"권환이 없습니다.");
//        }
//    }
//    // URI 화이트리스트에 있는지 확인하는 메서드
//    private boolean whiteListCheck(String uri) {
//        return PatternMatchUtils.simpleMatch(whiteListUris, uri);
//    }
//    // 요청에 토큰이 포함되어 있는지 확인하는 메서드
//    private boolean isContainToken(HttpServletRequest httpServletRequest) {
//        String authorization = httpServletRequest.getHeader(JwtProperties.ACCESS_TOKEN_HEADER);
//        return authorization != null && authorization.startsWith(JwtProperties.ACCESS_TOKEN_PREFIX);
//    }
//
//    // 토큰 가져오는 메서드
//    private String getToken(HttpServletRequest httpServletRequest) {
//        String authorization = httpServletRequest.getHeader(JwtProperties.ACCESS_TOKEN_HEADER);
//        return authorization.substring(7);
//    }
//    // Jwt 토큰에서 사용자 인증 정보를 가져오는 메서드
//    private AuthenticateMember getAuthenticateMember(String token) throws JsonProcessingException {
//        Jws<Claims> claimsJws = jwtProvider.parseClaims(token);
//        Claims claims = claimsJws.getPayload();
//        String authenticateMemberJson = claims.get(VerifyUserFilter.AUTHENTICATE_USER, String.class);
//        return objectMapper.readValue(authenticateMemberJson, AuthenticateMember.class);
//    }
//    // 사용자의 권환을 확인하는 메서드
//    private void verifyAuthorization(String uri, AuthenticateMember member) throws AccessDeniedException {
//        if(PatternMatchUtils.simpleMatch("*/admin", uri) && !member.username().contains(Role.ADMIN)) {
//            throw new AccessDeniedException("사용자 권한이 없습니다.");
//        }
//        if(PatternMatchUtils.simpleMatch("*/member/**", uri && !member.username().contains(Role.USER))) {
//            throw new AccessDeniedException("일반 사용자 권한이 없습니다.");
//        }
//        if(PatternMatchUtils.simpleMatch("*/admin/**", uri && member.username().contains(Role.USER))) {
//            throw new AccessDeniedException("어드민 접근 권환이 없습니다.");
//        }
//    }
}
