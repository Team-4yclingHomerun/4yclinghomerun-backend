package com.example.demo.oauth;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

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
@RequestMapping("/oauth")
public class OauthController {

    private final OauthService oauthService;

    @SneakyThrows
    @GetMapping("/login/{oauthServerType}")
    ResponseEntity<?> redirectAuthCodeRequestUrl
            (@PathVariable OauthServerType oauthServerType,
             HttpServletResponse response) {
        String redirectUrl = oauthService.getAuthCodeRequestUrl(oauthServerType);
        response.sendRedirect(redirectUrl);
        return ResponseEntity.ok().build();
    }
}
