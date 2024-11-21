package com.example.demo.member.service;

import com.example.demo.auth.AuthenticateMember;
import com.example.demo.member.dto.MemberSignInRequest;
import com.example.demo.member.dto.MemberSignInResponse;
import com.example.demo.member.dto.MemberVerifyResponse;
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
    MemberSignInResponse signIn(MemberSignInRequest signInRequest);
    MemberVerifyResponse verifyUser(MemberSignInRequest signInRequest);

    String getNicknameFromAuthenticateMember(AuthenticateMember authenticateMember);
}
