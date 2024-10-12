package com.example.demo.exception;

import org.springframework.http.HttpStatus;

/**
 * packageName    : com.example.demo.exception
 * fileName       : SignUpErroCode
 * author         : JAEIK
 * date           : 10/12/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/12/24        JAEIK       최초 생성
 */
public enum SignUpErrorCode implements ErrorCode{
    CONFLICTED_USERNAME("이미 존재하는 아이디입니다.", HttpStatus.CONFLICT),
    CONFLICTED_NICKNAME("이미 존재하는 닉네임입니다.", HttpStatus.CONFLICT),
    CONFLICTED_EMAIL("이미 존재하는 이메일입니다.", HttpStatus.CONFLICT);

    private final String message;
    private final HttpStatus status;

    SignUpErrorCode(String message, HttpStatus status) {
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
    public CustomException defaultException() {  //CustomException RuntimeException 상속함
        return new CustomException(this);
    }

    @Override
    public CustomException defaultException(Throwable cause) {
        return new CustomException(this, cause);
    }
}
