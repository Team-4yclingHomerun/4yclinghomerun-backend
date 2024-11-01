package com.example.demo.member.dto;

/**
 * packageName    : com.example.demo.member.dto
 * fileName       : NicknameCheckResponse
 * author         : JAEIK
 * date           : 10/25/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/25/24        JAEIK       최초 생성
 */
public record NicknameCheckResponse(
        String message,
        String nickname
) {
}
