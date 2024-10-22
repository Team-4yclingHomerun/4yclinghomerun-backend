package com.example.demo.email;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
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
public class MailSendService {

    private final JavaMailSender javaMailSender;

    private final String SUBJECT = "[4yclinghomerun 서비스] 인증메일입니다.";

    public void mailSend(String toMail, String certificationNumber) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true);

            String htmlContent = getCertificationMessage(certificationNumber);

            mimeMessageHelper.setTo(toMail);
            mimeMessageHelper.setSubject(SUBJECT);
            mimeMessageHelper.setText(htmlContent);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private String getCertificationMessage(String certificationNumber) {
        String certificationMessage = "";
        certificationMessage += "<h1 style='text-align: center;'> [4yclinghomerun 서비스] 인증 메일</h1>";
        certificationMessage +=
                "<h3 style='text--align: center;'> " +
                "인증코드 : <strong style='fond-size: 32px; letter-spacing: 8px;'>" +
                certificationNumber + "<strong></h3>";
        return certificationMessage;
    }
}
