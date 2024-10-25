package com.example.demo.member.dto;

/**
 * packageName    : com.example.demo.member.dto
 * fileName       : UsernameCheckResponse
 * author         : JAEIK
 * date           : 10/25/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/25/24        JAEIK       최초 생성
 */
public record UsernameCheckResponse(
        String message,
        String username
) {
}
