package com.example.demo.member.service;

import com.example.demo.jwt.JwtProvider;
import com.example.demo.jwt.JwtToken;
import com.example.demo.member.dto.MemberSignInRequest;
import com.example.demo.member.dto.MemberSignInResponse;
import com.example.demo.member.dto.MemberSignUpRequest;
import com.example.demo.member.dto.MemberVerifyResponse;
import com.example.demo.member.entity.Member;
import com.example.demo.member.entity.Role;
import com.example.demo.member.mapper.MemberDtoMapper;
import com.example.demo.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    @Override
    public Member signUp(MemberSignUpRequest dto) {
        Member member = memberDtoMapper.toEntity(dto);
        return signUp(member);
    }

    @Override
    public void deleteMember(UUID id) {
        memberRepository.deleteById(id);
    }

    @Override
    public MemberSignInResponse signIn(MemberSignInRequest signInRequest) {
//        MemberVerifyResponse verifyResponse = verifyUser(signInRequest);
//        System.out.println("Verify Response: " + verifyResponse.isValid());
//        if (!verifyResponse.isValid()) {
//            throw new IllegalArgumentException("로그인가 비밀번호가 잘못되었습니다.");
//        }
        // Role role = verifyResponse.role();
        JwtToken jwtToken = jwtProvider.createJwtToken(signInRequest.username());
        System.out.println("Generated JWT Token: " + jwtToken.getAccessToken());
        return new MemberSignInResponse(signInRequest.username(), signInRequest.password(), jwtToken.getAccessToken());
    }

    @Override
    public MemberVerifyResponse verifyUser(MemberSignInRequest signInRequest) {
        Member member = memberRepository.findByUsername(signInRequest.username());
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
