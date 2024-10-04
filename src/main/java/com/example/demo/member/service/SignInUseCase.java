package com.example.demo.member.service;

import com.example.demo.member.dto.MemberSignUpRequest;
import com.example.demo.member.entity.Member;

/**
 * packageName    : com.example.demo.member.service
 * fileName       : SignInUseCase
 * author         : JAEIK
 * date           : 10/2/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/2/24        JAEIK       최초 생성
 */
public interface SignInUseCase {
    Member signUp(Member member);
    Member signUp(MemberSignUpRequest member);
}
