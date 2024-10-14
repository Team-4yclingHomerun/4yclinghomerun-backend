package com.example.demo.member.service;

import com.example.demo.member.dto.MemberSignUpRequest;
import com.example.demo.member.entity.Member;
import com.example.demo.member.entity.MemberStatus;
import com.example.demo.member.entity.Role;
import com.example.demo.member.entity.Roles;

import java.time.Instant;
import java.util.UUID;

/**
 * packageName    : com.example.demo.member.service
 * fileName       : SignUpUseCase
 * author         : JAEIK
 * date           : 10/2/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/2/24        JAEIK       최초 생성
 */
public interface SignUpUseCase {
    Member signUp(Member member);
    Member signUp(MemberSignUpRequest member, MemberStatus status, Instant createAt, Instant updateAt);
    void deleteMember(UUID id);
}
