package com.example.demo.auth;

import com.example.demo.member.entity.Role;

/**
 * packageName    : com.example.demo.auth
 * fileName       : AuthenticateMember
 * author         : JAEIK
 * date           : 10/9/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/9/24        JAEIK       최초 생성
 */
public record AuthenticateMember(
        String username

        //Role role
) {
}
