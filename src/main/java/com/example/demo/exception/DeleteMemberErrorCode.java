package com.example.demo.exception;

import org.springframework.http.HttpStatus;

/**
 * packageName    : com.example.demo.exception
 * fileName       : DeleteMemberErrorCode
 * author         : JAEIK
 * date           : 10/12/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/12/24        JAEIK       최초 생성
 */
public enum DeleteMemberErrorCode implements ErrorCode{
    MEMBER_NOT_FOUND("회원이 존재하지 않습니다.", HttpStatus.NOT_FOUND),
    DELETE_PERMISSION_DENIED("삭제 권환이 없습니다.", HttpStatus.FORBIDDEN),
    DATABASE_ERROR("데이터베이스 오류입니다.", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String message;
    private final HttpStatus status;

    DeleteMemberErrorCode(String message, HttpStatus status) {
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
    public RuntimeException defaultException(Throwable cause) {
        return new CustomException(this, cause);
    }
}
