package com.example.demo.member.service;

import com.example.demo.auth.AuthenticateMember;
import com.example.demo.exception.CustomException;
import com.example.demo.exception.DeleteMemberErrorCode;
import com.example.demo.exception.SignInErrorCode;
import com.example.demo.exception.SignUpErrorCode;
import com.example.demo.jwt.JwtToken;
import com.example.demo.member.dto.*;
import com.example.demo.member.entity.Member;
import com.example.demo.member.entity.MemberStatus;
import com.example.demo.member.entity.Role;
import com.example.demo.member.entity.Roles;
import com.example.demo.member.mapper.MemberDtoMapper;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.member.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.Instant;
import java.util.*;

import static org.hibernate.validator.internal.util.Contracts.assertTrue;

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
@Slf4j
@RequiredArgsConstructor // final, non-null fields
public class MemberService
        implements SignUpUseCase, SignInUseCase, PasswordResetUseCase {


    private final MemberRepository memberRepository;
    private final RoleRepository roleRepository;
    private final MemberDtoMapper memberDtoMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtAuthenticationProxyService jwtProvider;

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
    public Member signUp(MemberSignUpRequest request, MemberStatus status, Instant createAt, Instant updateAt) {
        if (memberRepository.existsByUsername(request.username())) {
            throw new CustomException(SignUpErrorCode.CONFLICTED_USERNAME);
        }
        if (memberRepository.existsByEmail(request.email())) {
            throw new CustomException(SignUpErrorCode.CONFLICTED_EMAIL);
        }
        if (memberRepository.existsByNickname(request.nickname())) {
            throw new CustomException(SignUpErrorCode.CONFLICTED_NICKNAME);
        }
        Roles userRole = roleRepository.findByRoleName(Role.USER);
        if (userRole == null) {
            throw new IllegalArgumentException("권한을 찾을 수 없습니다.");
        }
        Member member = memberDtoMapper.toEntity(request, status, createAt, updateAt, userRole);
        member.addRole(userRole);
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
            member.get().setToDeletedMember();
            memberRepository.save(member.get());
        } catch (Exception e) {
            throw new CustomException(DeleteMemberErrorCode.DATABASE_ERROR, e);
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
        // 권환 가져옴
        if (member.getRoles().isEmpty()) {
            throw SignInErrorCode.NO_ROLE_FOUND_MEMBER.defaultException();
        }

        Role role = null;
        for (var item : member.getRoles()) {
            role = item.getRoleName();
        }

        JwtToken jwtToken = jwtProvider.createJwtToken(member.getId(), member.getUsername(), role);
        return new MemberSignInResponse(signInRequest.username(), signInRequest.password(), jwtToken.getAccessToken());
    }

    // 아이디 중복체크
    public UsernameCheckResponse checkUsernameAvailability(UsernameCheckRequest request) {
        if (memberRepository.existsByUsername(request.username())) {
            throw SignUpErrorCode.CONFLICTED_USERNAME.defaultException();
        }
        return new UsernameCheckResponse("사용 가능한 아이디입니다.", request.username());
    }
    // 닉네임 중복체크
    public NicknameCheckResponse checkNicknameAvailability(NicknameCheckRequest request) {
        if (memberRepository.existsByNickname(request.nickName())) {
            throw SignUpErrorCode.CONFLICTED_NICKNAME.defaultException();
        }
        return new NicknameCheckResponse("사용 가능한 닉네임입니다.", request.nickName());
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
                .id(member.getId())
                .roles(getAllRoles(member))
                .build();
    }

    private Set<Role> getAllRoles(Member member) {
        Set<Role> roles = new HashSet<>();
        for (Roles role : member.getRoles()) {
            roles.add(role.getRoleName());
        }
        return roles;
    }
}
