package com.example.demo.oauth.controller;

import com.example.demo.jwt.JwtToken;
import com.example.demo.oauth.OauthServerType;
import com.example.demo.oauth.service.OauthService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * packageName    : com.example.demo.oauth
 * fileName       : OauthController
 * author         : JAEIK
 * date           : 10/17/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/17/24        JAEIK       최초 생성
 */
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/oauth")
public class OauthController {

    private final OauthService oauthService;

    @SneakyThrows
    @Operation(
            summary = "소셜로그인 리다이렉션 주소(로그인창)",
            description = "사용자가 소셜 로그인 할 수 있도록 로그인 창으로 리아디렉션 합니다.")
    @GetMapping("/{oauthServerType}")
    ResponseEntity<?> redirectAuthCodeRequestUrl
            (@PathVariable OauthServerType oauthServerType,
             HttpServletResponse response) {
        String redirectUrl = oauthService.getAuthCodeRequestUrl(oauthServerType);
        log.info("Redirect URL: {}", redirectUrl);
        System.out.println(redirectUrl);
        response.sendRedirect(redirectUrl);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "소셜로그인",
            description = "사용자가 OAuth 서버에 로그인 하여 인증 코드를 사용해서 로그인 한다.")
    @GetMapping("/login/{oauthServerType}")
    ResponseEntity<?> login(
            @PathVariable OauthServerType oauthServerType,
            @RequestParam("code") String code
    ) {
        log.info("OAuth Server Type: {}, Code: {}", oauthServerType, code);
        JwtToken login = oauthService.login(oauthServerType, code);
        log.info("Login Successful: {}", login);
        return ResponseEntity.ok(login);
    }
}
