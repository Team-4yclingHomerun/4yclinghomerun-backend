package com.example.demo.member.dto;

/**
 * packageName    : com.example.demo.member.dto
 * fileName       : MemberSignInResponse
 * author         : JAEIK
 * date           : 10/9/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/9/24        JAEIK       최초 생성
 */
public record MemberSignInResponse(
        String username,
        String password,
        String token
) {
}
