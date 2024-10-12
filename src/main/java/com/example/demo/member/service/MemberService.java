package com.example.demo.member.service;

import com.example.demo.auth.AuthenticateMember;
import com.example.demo.exception.CustomException;
import com.example.demo.exception.DeleteMemberErrorCode;
import com.example.demo.exception.SignInErrorCode;
import com.example.demo.exception.SignUpErrorCode;
import com.example.demo.jpa.support.UuidBaseEntity;
import com.example.demo.jwt.JwtProvider;
import com.example.demo.jwt.JwtToken;
import com.example.demo.member.dto.*;
import com.example.demo.member.entity.Member;
import com.example.demo.member.entity.Role;
import com.example.demo.member.mapper.MemberDtoMapper;
import com.example.demo.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

/**
 * packageName    : com.example.demo.member.service
 * fileName       : memberService
 * author         : JAEIK
 * date           : 10/2/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/2/24        JAEIK       최초 생성
 */
@Service
@RequiredArgsConstructor // final, non-null fields
public class MemberService
        implements SignUpUseCase, SignInUseCase, PasswordResetUseCase {

    private final MemberRepository memberRepository;
    private final MemberDtoMapper memberDtoMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Override
    public Member signUp(Member member) {
        if (member.getPassword() == null) {
            throw new IllegalArgumentException("Password cannot be null");
        }
        String encodedPassword = passwordEncoder.encode(member.getPassword());
        member.updatePassword(encodedPassword);
        return memberRepository.save(member);
    }
    // 회원가입
    @Override
    public Member signUp(MemberSignUpRequest request) {
        if (memberRepository.existsByUsername(request.username())) {
            throw new CustomException(SignUpErrorCode.CONFLICTED_USERNAME);
        }
        if (memberRepository.existsByEmail(request.email())) {
            throw new CustomException(SignUpErrorCode.CONFLICTED_EMAIL);
        }
        if (memberRepository.existsByNickname(request.nickname())) {
            throw new CustomException(SignUpErrorCode.CONFLICTED_USERNAME);
        }
        Member member = memberDtoMapper.toEntity(request);
        return signUp(member);
    }
    // 회원 삭제
    @Override
    public void deleteMember(UUID id) {
        Optional<Member> member = memberRepository.findById(id);
        if (member.isEmpty()) {
            throw new CustomException(DeleteMemberErrorCode.MEMBER_NOT_FOUND);
        }
        try {
            memberRepository.deleteById(id);
        } catch (CustomException ce) {
            throw new CustomException(DeleteMemberErrorCode.DATABASE_ERROR);
        }
    }
    // 로그인
    @Override
    public MemberSignInResponse signIn(MemberSignInRequest signInRequest) {
            Member member = memberRepository.findByUsername(signInRequest.username())
                    .orElseThrow(() -> new CustomException(SignInErrorCode.NOT_FOUND_USERNAME));
            if (!passwordEncoder.matches(signInRequest.password(), member.getPassword())) {
                throw new CustomException(SignInErrorCode.PASSWORD_MISMATCH);
            }
            JwtToken jwtToken = jwtProvider.createJwtToken(signInRequest.username());
            return new MemberSignInResponse(signInRequest.username(), signInRequest.password(), jwtToken.getAccessToken());
    }
    // 아이디, 패스워드 인증 메서드
    @Override
    public MemberVerifyResponse verifyUser(MemberSignInRequest signInRequest) {
        Member member = memberRepository.findByUsername(signInRequest.username())
                .orElseThrow(()->new CustomException(SignInErrorCode.NOT_FOUND_USERNAME));
        if (member == null) {
            return MemberVerifyResponse.builder()
                    .isValid(false)
                    .build();
        }
        boolean passwordMatches = passwordEncoder.matches(signInRequest.password(), member.getPassword());
        if (!passwordMatches) {
            return MemberVerifyResponse.builder()
                    .isValid(false)
                    .build();
        }
        return MemberVerifyResponse.builder()
                .isValid(true)
                .build();
    }

}
