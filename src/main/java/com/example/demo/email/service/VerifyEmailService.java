package com.example.demo.email.service;

import com.example.demo.email.repository.EmailRepository;
import com.example.demo.email.dto.EmailCertificationResponse;
import com.example.demo.email.entity.VerifyEmailCode;
import com.example.demo.exception.SignUpErrorCode;
import com.example.demo.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ThreadLocalRandom;

/**
 * packageName    : com.example.demo.email
 * fileName       : VerifyEmailService
 * author         : JAEIK
 * date           : 10/22/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/22/24        JAEIK       최초 생성
 */
@Service
@RequiredArgsConstructor
public class VerifyEmailService {

    private final EmailRepository emailRepository;
    private final MemberRepository memberRepository;
    private final String CHAR = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    // 인증코드 랜덤키
    public String generateRandomCode(int length) {
        String charcters = CHAR;
        StringBuilder sb = new StringBuilder();
        ThreadLocalRandom random = ThreadLocalRandom.current();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(charcters.length());
            sb.append(charcters.charAt(index));
        }
        return sb.toString();
    }

    // 인증코드 생성 및 저장 (유효성검사를 위해)
    public VerifyEmailCode createVerifyCationCode(String email) {
        String certificationNumber = generateRandomCode(6);
        VerifyEmailCode code = VerifyEmailCode.builder()
                .email(email)
                .code(certificationNumber)
                .expiresTime(Instant.now().plus(1, ChronoUnit.HOURS))
                .build();
        return emailRepository.save(code);

    }

    // 유효성검사
    public void verifyCode(String email, String code) {
        VerifyEmailCode verifyEmailCode = emailRepository.findByEmailAndCode(email, code)
                .orElseThrow(SignUpErrorCode.EMAIL_VERIFY_FAIL::defaultException);

        if (verifyEmailCode.getExpiresTime().isBefore(Instant.now())) {
            throw SignUpErrorCode.CODE_EXPIRED.defaultException();
        }
    }

    // 이메일 중복
    public EmailCertificationResponse duplicateEmail(String email) {
        if (memberRepository.existsByEmail(email)) {
            throw SignUpErrorCode.CONFLICTED_EMAIL.defaultException();
        }
        return new EmailCertificationResponse("사용가능한 이메일 입니다.", email);
    }
}


