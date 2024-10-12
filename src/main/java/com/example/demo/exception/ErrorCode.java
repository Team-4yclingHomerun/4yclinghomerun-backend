package com.example.demo.exception;

import org.springframework.http.HttpStatus;

/**
 * packageName    : com.example.demo.exception
 * fileName       : ErrorCode
 * author         : JAEIK
 * date           : 10/12/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/12/24        JAEIK       최초 생성
 */
public interface ErrorCode {
    String name();
    String defaultMessage();
    HttpStatus defaultHttpStatus();
    RuntimeException defaultException();
    RuntimeException defaultException(Throwable cause); // 에러 콜스택을 추가 한다.
}
