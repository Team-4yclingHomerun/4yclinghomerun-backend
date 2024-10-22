package com.example.demo.email.service;

import com.example.demo.email.dto.EmailCertificationResponse;
import com.example.demo.email.entity.VerifyEmailCode;
import com.example.demo.exception.SignUpErrorCode;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 * packageName    : com.example.demo.email
 * fileName       : MailSendService
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
public class EmailSendService {

    private final JavaMailSender javaMailSender;

    private final VerifyEmailService verifyEmailService;
    private final String SUBJECT = "[4yclinghomerun 서비스] 인증메일입니다.";

    public EmailCertificationResponse mailSend(String toMail) {
        VerifyEmailCode createCode = verifyEmailService.createVerifyCationCode(toMail);
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true);

            String htmlContent = getCertificationMessage(createCode);

            mimeMessageHelper.setTo(toMail);
            mimeMessageHelper.setSubject(SUBJECT);
            mimeMessageHelper.setText(htmlContent,true);

            javaMailSender.send(message);

        } catch (MessagingException e) {
            System.err.println("Failed to send email to: " + toMail);
            e.printStackTrace();
            throw SignUpErrorCode.EMAIL_SEND_FAIL.defaultException();
        }
        return new EmailCertificationResponse("이메일 전송 성공", toMail);
    }

    private String getCertificationMessage(VerifyEmailCode createCode) {
        String certificationMessage = "";
        certificationMessage += "<h1 style='text-align: center;'> [4yclinghomerun 서비스] 인증 메일</h1>";
        certificationMessage +=
                "<h3 style='text-align: center;'> " +
                "인증코드 : <strong style='font-size: 32px; letter-spacing: 8px;'>" +
                createCode.getCode() + "</strong></h3>";
        return certificationMessage;
    }
}
