package com.example.demo.email.controller;

import com.example.demo.email.dto.EmailSignUpOrLoginResponse;
import com.example.demo.email.service.EmailSendService;
import com.example.demo.email.service.VerifyEmailService;
import com.example.demo.email.dto.EmailCertificationRequest;
import com.example.demo.email.dto.EmailCertificationResponse;
import com.example.demo.email.dto.VerifyCheckEmailRequest;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * packageName    : com.example.demo.email
 * fileName       : EmailController
 * author         : JAEIK
 * date           : 10/22/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/22/24        JAEIK       최초 생성
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class EmailController {

    private final EmailSendService emailSendService;
    private final VerifyEmailService verifyEmailService;

    @Operation(summary = "인증메일 전송", description = "사용자가 인증메일을 받습니다.")
    @PostMapping("/send-email")
    public ResponseEntity<?> emailCertification(@Valid @RequestBody EmailCertificationRequest certificationRequest) {
        EmailCertificationResponse response = emailSendService.mailSend(certificationRequest.email());
        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "인증 유효성검사", description = "인증코드를 받고 유효성검사를 합니다.")
    @PostMapping("/verify-email")
    public ResponseEntity<?> verifyCheckEmail(@Valid @RequestBody VerifyCheckEmailRequest checkEmailRequest) {
        verifyEmailService.verifyCode(checkEmailRequest.email(), checkEmailRequest.code());
        return ResponseEntity.ok().body("인증이 완료 되었습니다.");

    }

    @Operation(summary = "이메일 중복검사", description = "중복확인 후 중복확인되면 로그인 없으면 회원가입을 합니다.")
    @PostMapping("duplicate-email")
    public ResponseEntity<?> duplicateCheckEmail(@RequestBody EmailCertificationRequest certificationRequest) {
        EmailSignUpOrLoginResponse response = verifyEmailService.duplicateEmail(certificationRequest.email());
        return ResponseEntity.ok().body(response);
    }
}
