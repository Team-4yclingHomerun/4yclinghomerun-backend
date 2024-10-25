package com.example.demo.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * packageName    : com.example.demo.member.dto
 * fileName       : NicknameCheckRequest
 * author         : JAEIK
 * date           : 10/25/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/25/24        JAEIK       최초 생성
 */
public record NicknameCheckRequest(
        @Schema(description = "닉네임", example = "취뽀홈런")
        @NotBlank(message = "닉네임을 입력하세요")
        @Pattern(
                regexp = "^[A-Za-z\\d가-힣ㄱ-ㅣ]+$",
                message = "닉네임은 영문 대소문자, 한글, 숫자만 입력 가능합니다."
        )
        @Size(min = 3, message = "닉네임은 3글자 이상이어야 합니다.")
        @Size(max = 30, message = "닉네임은 30글자 이하여야 합니다.")
        String nickName
) {
}
