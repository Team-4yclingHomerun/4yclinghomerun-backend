package com.example.demo.member.service;

import com.example.demo.member.dto.MemberSignUpRequest;
import com.example.demo.member.entity.Member;
import com.example.demo.member.mapper.MemberDtoMapper;
import com.example.demo.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    @Override
    public Member signUp(Member member) {
        if (member.getPassword() == null) {
            throw new IllegalArgumentException("Password cannot be null");
        }
         String encodedPassword = passwordEncoder.encode(member.getPassword());
         member.updatePassword(encodedPassword);
         System.out.println(encodedPassword);
         return memberRepository.save(member);
    }

    @Override
    public Member signUp(MemberSignUpRequest dto) {
        System.out.println("Received password: " + dto.password());
        Member member = memberDtoMapper.toEntity(dto);
        System.out.println("Mapped Member password: " + member.getPassword());
        return signUp(member);
    }
}
