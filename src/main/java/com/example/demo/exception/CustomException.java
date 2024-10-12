package com.example.demo.exception;

import lombok.Getter;

/**
 * packageName    : com.example.demo.exception
 * fileName       : CustomExeption
 * author         : JAEIK
 * date           : 10/12/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/12/24        JAEIK       최초 생성
 */
@Getter
public class CustomException extends RuntimeException {
    protected final ErrorCode errorCode;

    public CustomException(ErrorCode errorCode) {
        super(errorCode.defaultMessage()); // 부모클래스 생성자 호출
        this.errorCode = errorCode;
    }
    public CustomException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.defaultMessage(), cause);
        this.errorCode = errorCode;
    }

}
