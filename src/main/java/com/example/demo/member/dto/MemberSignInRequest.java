package com.example.demo.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;

/**
 * packageName    : com.example.demo.member.dto
 * fileName       : MemberSignInRequest
 * author         : JAEIK
 * date           : 10/5/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/5/24        JAEIK       최초 생성
 */
@Builder
public record MemberSignInRequest(
        @Schema(description = "로그인 아이디", example = "user95")
        @NotBlank(message = "사용자 이름을 입력해주세요.")
        @Pattern(regexp = "^[A-Za-z\\d]+$",
        message = "사용자 이름은 영문 대소문자와 숫자만 입력 가능합니다.")
        @Size(min = 3 ,max = 30 , message = "사용자 이름은 3글자 이상, 30글자 이하여야 합니다." )
        String username,
        @Schema(description = "비밀번호", example = "kim12345")
        @NotBlank(message = "패스워드를 입력하세요.")
        @Pattern(regexp = "^[A-Za-z\\d~!@#$%^&*?_=\\-+,./:;]+$",
        message = """
                 패스워드는 영문 대소문자, 숫자, 다음 특수문자만 입력 가능합니다.
                 (~!@#$%^&*?_=-+,./:;)""")
        @Size(min = 8 ,max = 100 , message = "패스워드는 8글자 이상 100글자 이하여야 합니다.")
        String password
) {
}
