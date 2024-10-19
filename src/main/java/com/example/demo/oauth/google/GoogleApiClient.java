package com.example.demo.oauth.google;

import com.example.demo.jwt.JwtProperties;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * packageName    : com.example.demo.oauth.google
 * fileName       : GoogleApiClient
 * author         : JAEIK
 * date           : 10/20/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/20/24        JAEIK       최초 생성
 */
public interface GoogleApiClient {

    GoogleToken fetchToken(@RequestParam MultiValueMap<String, String> params);

    GoogleMemberResponse fetchMember(@RequestHeader(name = JwtProperties.ACCESS_TOKEN_HEADER) String bearerToken);
}
