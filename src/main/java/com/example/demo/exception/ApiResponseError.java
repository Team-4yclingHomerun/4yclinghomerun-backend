package com.example.demo.exception;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * packageName    : com.example.demo.exception
 * fileName       : ApiResponseError
 * author         : JAEIK
 * date           : 10/12/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/12/24        JAEIK       최초 생성
 */
@Getter
public class ApiResponseError {
    private final String errorCode;
    private final String message;
    private final HttpStatus status;

    @Builder
    public ApiResponseError(String errorCode, String message, HttpStatus status) {
        this.errorCode = errorCode;
        this.message = message;
        this.status = status;
    }
    public static ApiResponseError of(CustomException exception) {
        return  ApiResponseError.builder()
                .errorCode(exception.getErrorCode().name())
                .message(exception.getMessage())
                .status(exception.getErrorCode().defaultHttpStatus())
                .build();
    }
}
