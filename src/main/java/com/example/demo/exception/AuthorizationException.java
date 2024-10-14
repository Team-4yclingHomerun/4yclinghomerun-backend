package com.example.demo.exception;

/**
 * packageName    : com.example.demo.exception
 * fileName       : AuthorizationException
 * author         : JAEIK
 * date           : 10/14/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/14/24        JAEIK       최초 생성
 */
public class AuthorizationException extends CustomException {
    public AuthorizationException(ErrorCode errorCode) {
        super(errorCode);
    }

    public AuthorizationException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }
}
