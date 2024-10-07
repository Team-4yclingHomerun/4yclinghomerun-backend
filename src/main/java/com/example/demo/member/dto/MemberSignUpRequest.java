package com.example.demo.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * packageName    : com.example.demo.member.dto
 * fileName       : MemberRequestDto
 * author         : JAEIK
 * date           : 10/2/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/2/24        JAEIK       최초 생성
 */
@Builder
@Slf4j
public record MemberSignUpRequest(
        @Schema(description = "로그인 아이디", example = "user95")
        @NotBlank(message = "사용자 이름을 입력하세요")
        @Pattern(
                regexp = "^[A-Za-z\\d]+$",
                message = "사용자 이름은 영문 대소문자와 숫자만 입력 가능합니다."
        )
        @Size(min = 3, message = "사용자 이름은 3글자 이상이어야 합니다.")
        @Size(max = 30, message = "사용자 이름은 30글자 이하여야 합니다.")
        String username,

        @Schema(description = "비밀번호", example = "kim12345!")
        @NotBlank(message = "패스워드를 입력하세요")
        @Pattern(
                regexp = "^[A-Za-z\\d~!@#$%^&*?_=\\-+,./:;]+$",
                message = """
                        패스워드는 영문 대소문자, 숫자, 다음 특수문자만 입력 가능합니다.
                        (~!@#$%^&*?_=-+,./:;)"""
        )
        @Size(min = 8, message = "패스워드는 8글자 이상이어야 합니다.")
        @Size(max = 100, message = "패스워드는 100글자 이하여야 합니다.")
        String password,

        @Schema(description = "이메일", example = "kim123@naver.com")
        @NotBlank(message = "이메일을 입력하세요")
        @Pattern(
                regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
                message = "이메일은 영문 대소문자, 숫자, @ 및 도메인 형식을 포함해야합니다."
        )
        @Size(min =6, message = "이메일은 6글자 이상이어야 합니다.")
        @Size(max =100, message = "이메일은 100글자 이하여야 합니다.")
        String  email,

        @Schema(description = "닉네임", example = "취뽀홈런")
        @NotBlank(message = "닉네임을 입력하세요")
        @Pattern(
                regexp = "^[A-Za-z\\d가-힣ㄱ-ㅣ]+$",
                message = "닉네임은 영문 대소문자, 한글, 숫자만 입력 가능합니다."
        )
        @Size(min = 3, message = "닉네임은 3글자 이상이어야 합니다.")
        @Size(max = 30, message = "닉네임은 30글자 이하여야 합니다.")
        String nickname
) {
}
