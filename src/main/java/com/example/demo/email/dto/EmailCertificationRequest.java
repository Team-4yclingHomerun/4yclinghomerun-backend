package com.example.demo.email.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * packageName    : com.example.demo.email.dto
 * fileName       : EmialCertificationRequest
 * author         : JAEIK
 * date           : 10/22/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/22/24        JAEIK       최초 생성
 */
public record EmailCertificationRequest(

        @Schema(description = "이메일", example = "kim123@naver.com")
        @NotBlank(message = "이메일을 입력하세요")
        @Pattern(
                regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
                message = "이메일은 영문 대소문자, 숫자, @ 및 도메인 형식을 포함해야합니다."
        )
        @Size(min =6, message = "이메일은 6글자 이상이어야 합니다.")
        @Size(max =100, message = "이메일은 100글자 이하여야 합니다.")
        String email
) {

}
