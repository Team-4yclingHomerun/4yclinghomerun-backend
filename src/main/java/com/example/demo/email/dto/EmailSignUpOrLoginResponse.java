package com.example.demo.email.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * packageName    : com.example.demo.email.dto
 * fileName       : EmailSignUpOrLoginResponse
 * author         : JAEIK
 * date           : 10/25/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/25/24        JAEIK       최초 생성
 */
public record EmailSignUpOrLoginResponse(
        String message,
        String email,
        @JsonInclude(Include.NON_NULL)
        @JsonProperty("access_token")
        String accessToken
) {
}
