package com.example.demo.jwt;

import com.example.demo.auth.AuthenticateMember;
import com.example.demo.auth.properties.DemoAuthorizationPathProperties;
import com.example.demo.member.entity.Role;
import com.fasterxml.jackson.core.JsonParseException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import static com.example.demo.auth.AuthorizationConstants.LOGIN_MEMBER_ATTRIBUTE;
import static com.example.demo.jwt.JwtProperties.ACCESS_TOKEN_PREFIX;

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
public class JwtAuthorizationFilter implements Filter {

    // 인증 없이 접근 가능한 URI
    private final String[] whiteListUris = {
            "/yclinghomerun/signUp",
            "/yclinghomerun/login",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/public/**",
            "/oauth/*",
            "/oauth/login/kakao",
            "/oauth/login/google",
            "/login/auth/redirected/kakao",
            "/login/auth/redirected/google"
    };
    private final JwtParser jwtParser;
    private final DemoAuthorizationPathProperties authorizationPathProperties;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        // 요청 URI 화이트리스트에 있는지 확인
        if (whiteListCheck(httpServletRequest.getRequestURI()) || isSwaggerRequest(httpServletRequest.getRequestURI())) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        // 요청 토큰이 포함되어 있는지 확인
        String authorization = httpServletRequest.getHeader(JwtProperties.ACCESS_TOKEN_HEADER);

        if (!hasAuthorizationWithPrefix(authorization)) {
            httpServletResponse.sendError(HttpStatus.UNAUTHORIZED.value(), "인증 오류");
            return;
        }
        try {
            // JWT 토큰 추출
            String token = authorization.substring(ACCESS_TOKEN_PREFIX.length());
            // JWT 기반으로 사용자 인증정보 추출
            AuthenticateMember authenticateMember = getAuthenticateMember(token);
            // 사용자의 권환 확인
            verifyAuthorization(httpServletRequest.getMethod(), httpServletRequest.getRequestURI(), authenticateMember);
            log.info("값 : {}", authenticateMember.username());

            // request에 속성 추가
            httpServletRequest.setAttribute(LOGIN_MEMBER_ATTRIBUTE, authenticateMember);

            filterChain.doFilter(servletRequest, servletResponse);
        } catch (JsonParseException e) {
            log.error("Json 파싱 예외 발생");
            httpServletResponse.sendError(HttpStatus.BAD_REQUEST.value());
        } catch (MalformedJwtException | UnsupportedJwtException e) {
            log.error("Jwt 예외 발생");
            httpServletResponse.sendError(HttpStatus.UNAUTHORIZED.value(), "인증 오류");
        } catch (ExpiredJwtException e) {
            log.error("Jwt 토큰이 만료되었습니다.");
            httpServletResponse.sendError(HttpStatus.FORBIDDEN.value(), "토큰이 만료 되었습니다.");
        } catch (AccessDeniedException e) {
            log.error("권환 예외 발생");
            httpServletResponse.sendError(HttpStatus.UNAUTHORIZED.value(),"권환이 없습니다.");
        }
    }

    // URI 화이트리스트에 있는지 확인하는 메서드
    private boolean whiteListCheck(String uri) {
        log.info("요청 URI: {}", uri);
        return PatternMatchUtils.simpleMatch(whiteListUris, uri);
    }
    private boolean isSwaggerRequest(String uri) {
        return uri.startsWith("/swagger-ui") || uri.startsWith("/v3/api-docs");
    }

    private boolean hasAuthorizationWithPrefix(String authorizationToken) {
        return authorizationToken != null && authorizationToken.startsWith(ACCESS_TOKEN_PREFIX);
    }

    // Jwt 토큰에서 사용자 인증 정보를 가져오는 메서드
    private AuthenticateMember getAuthenticateMember(String token) {
        Map<String, Object> claims = jwtParser.parseClaims(token);

        if (!(claims.get("id") instanceof String uuidString)) {
            throw new Error("...");
        }
        if (!(claims.get("username") instanceof String username)) {
            throw new Error("...");
        }
        if (!(claims.get("role") instanceof String roleString)) { // "USER"
            throw new Error("...");
        }

        UUID id = UUID.fromString(uuidString);
        Role role = Role.valueOf(roleString);

        return new AuthenticateMember(id, username, Set.of(role));
    }

    // 사용자의 권환을 확인하는 메서드
    private void verifyAuthorization(String method, String uri, AuthenticateMember member) throws AccessDeniedException {
        Role[] uesrRoles = new Role[member.role().size()];
        member.role().toArray(uesrRoles);

        for (var authorizationUri : authorizationPathProperties.uris()) {
            for (var requiredMethod : authorizationUri.methods()) {
                boolean verified = verify(
                        method,
                        uri,
                        uesrRoles,
                        requiredMethod,
                        authorizationUri.pattern(),
                        authorizationUri.roles()
                );
                if (verified) {
                    return;
                }
            }
        }

        throw new AccessDeniedException("매칭되는 권한이 없습니다.");

//        switch (uesrRoles) {
//            case Role[] ignore
//                    when verify(method, uri, uesrRoles, "GET", "*/admin", Role.ADMIN) -> {}
//            case Role[] ignore
//                    when verify(method, uri, uesrRoles, "POST", "*/admin", Role.ADMIN) -> {}
//            case Role[] ignore
//                    when verify(method, uri, uesrRoles, "PUT", "*/admin", Role.ADMIN) ->  {}
//            case Role[] ignore
//                    when verify(method, uri, uesrRoles, "DELETE", "*/admin", Role.ADMIN) ->  {}
//            case Role[] ignore
//                    when verify(method, uri, uesrRoles, "GET", "*/admin/**", Role.ADMIN) ->  {}
//            case Role[] ignore
//                    when verify(method, uri, uesrRoles, "POST", "*/admin/**", Role.ADMIN) ->  {}
//            case Role[] ignore
//                    when verify(method, uri, uesrRoles, "PUT", "*/admin/**", Role.ADMIN) ->  {}
//            case Role[] ignore
//                    when verify(method, uri, uesrRoles, "DELETE", "*/admin/**", Role.ADMIN) ->  {}
//            default -> throw new Error();
//        };
    }

    private boolean verify(
            String currentMethod,
            String currentUri,
            Role[] currentRoles,
            String requiredMethod,
            String requiredUriPattern,
            Role... requiredRoles
    ) throws AccessDeniedException {
        if (!Objects.equals(currentMethod, requiredMethod)) {
            return false;
        }
        if(PatternMatchUtils.simpleMatch(requiredUriPattern, currentUri)) {
            int maskedRole = roleBits(currentRoles) & roleBits(requiredRoles);
            if (maskedRole == 0) {
                throw new AccessDeniedException(Arrays.toString(requiredRoles) + " 권한이 없습니다.");
            }
            return true;
        }
        return false;
    }

    private int roleBits(Role... roles) {
        int bit = 0;
        for (var role : roles) {
            bit |= role.bit();
        }
        return bit;
    }
}
