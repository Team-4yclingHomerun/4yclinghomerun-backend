package com.example.demo.exception;

import org.springframework.http.HttpStatus;

/**
 * packageName    : com.example.demo.exception
 * fileName       : SiginInErrorCode
 * author         : JAEIK
 * date           : 10/12/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/12/24        JAEIK       최초 생성
 */
public enum SignInErrorCode implements ErrorCode {
    NOT_FOUND_USERNAME("존재하지 않는 유저 입니다.", HttpStatus.NOT_FOUND),
    PASSWORD_MISMATCH("비밀번호가 일치하지 않습니다.", HttpStatus.UNAUTHORIZED),
    NO_ROLE_FOUND_MEMBER("권환이 등록되지 않는 유저입니다.", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String message;
    private final HttpStatus status;

    SignInErrorCode(String message, HttpStatus status) {
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
    public CustomException defaultException() {
        return new CustomException(this);
    }

    @Override
    public CustomException defaultException(Throwable cause) {
        return new CustomException(this, cause);
    }
}
