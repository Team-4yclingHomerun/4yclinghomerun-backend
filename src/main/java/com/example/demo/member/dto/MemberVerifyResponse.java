package com.example.demo.member.dto;

import com.example.demo.member.entity.Role;
import lombok.Builder;

/**
 * packageName    : com.example.demo.member.dto
 * fileName       : MemberVerifyResponse
 * author         : JAEIK
 * date           : 10/9/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/9/24        JAEIK       최초 생성
 */
@Builder
public record MemberVerifyResponse(
        boolean isValid       // Role role
) {
}
