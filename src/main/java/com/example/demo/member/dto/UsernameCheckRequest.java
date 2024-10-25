package com.example.demo.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * packageName    : com.example.demo.member.dto
 * fileName       : UsernameCheckRequest
 * author         : JAEIK
 * date           : 10/25/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/25/24        JAEIK       최초 생성
 */
public record UsernameCheckRequest(
        @Schema(description = "로그인 아이디", example = "user95")
        @NotBlank(message = "사용자 이름을 입력하세요")
        @Pattern(
                regexp = "^[A-Za-z\\d]+$",
                message = "사용자 이름은 영문 대소문자와 숫자만 입력 가능합니다."
        )
        @Size(min = 3, message = "사용자 이름은 3글자 이상이어야 합니다.")
        @Size(max = 30, message = "사용자 이름은 30글자 이하여야 합니다.")
        String username
) {
}
