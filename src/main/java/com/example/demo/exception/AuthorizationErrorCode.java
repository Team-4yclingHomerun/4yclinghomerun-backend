package com.example.demo.exception;

import org.springframework.http.HttpStatus;

/**
 * packageName    : com.example.demo.exception
 * fileName       : AuthorizationErrorCode
 * author         : JAEIK
 * date           : 10/14/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/14/24        JAEIK       최초 생성
 */
public enum AuthorizationErrorCode implements ErrorCode {
    INVALID_JWT("잘못된 토큰입니다.", HttpStatus.BAD_REQUEST);

    private final String message;
    private final HttpStatus status;

    AuthorizationErrorCode(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    @Override
    public String defaultMessage() {
        return message;
    }

    @Override
    public HttpStatus defaultHttpStatus() {
        return status;
    }

    @Override
    public AuthorizationException defaultException() {
        return new AuthorizationException(this);
    }

    @Override
    public AuthorizationException defaultException(Throwable cause) {
        return new AuthorizationException(this, cause);
    }
}
