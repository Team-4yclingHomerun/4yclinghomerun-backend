package com.example.demo.oauth.controller;

import com.example.demo.jwt.JwtToken;
import com.example.demo.oauth.OauthServerType;
import com.example.demo.oauth.kakao.dto.OauthCodeVerifyRequest;
import com.example.demo.oauth.service.OauthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
@Tag(name = "소셜로그인", description = "소셜로그인 API")
@RequestMapping("/api/oauth")
public class OauthController {

    private final OauthService oauthService;

    @SneakyThrows
    @Operation(
            summary = "소셜로그인 리다이렉션 주소(로그인창)",
            description = "사용자가 소셜 로그인 할 수 있도록 로그인 창을 띄어줍니다.")
    @GetMapping("/{oauthservertype}")
    ResponseEntity<?> redirectAuthCodeRequestUrl
            (@PathVariable OauthServerType oauthservertype,
             HttpServletResponse response) {
        String redirectUrl = oauthService.getAuthCodeRequestUrl(oauthservertype);
        log.info("Redirect URL: {}", redirectUrl);
        System.out.println(redirectUrl);
        response.sendRedirect(redirectUrl);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "소셜로그인",
            description = "사용자가 Oauth 서버에 로그인 하여 인증 코드를 사용해서 로그인 한다.")
    @PostMapping("/login/{oauthservertype}")
    ResponseEntity<?> login(
            @PathVariable OauthServerType oauthservertype,
            @RequestBody OauthCodeVerifyRequest requestCode
    ) {
        log.info("OAuth Server Type: {}, Code: {}", oauthservertype, requestCode);
        JwtToken login = oauthService.login(oauthservertype, requestCode.code());
        log.info("Login Successful: {}", login);
        return ResponseEntity.ok(login);
    }
}
