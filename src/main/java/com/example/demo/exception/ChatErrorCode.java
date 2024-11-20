package com.example.demo.exception;

import com.example.demo.exception.ErrorCode;
import org.springframework.http.HttpStatus;

/**
 * packageName    : com.example.demo.websocket
 * fileName       : ChatErrorCode
 * author         : JAEIK
 * date           : 11/20/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 11/20/24       JAEIK       최초 생성
 */
public enum ChatErrorCode implements ErrorCode {
    NOT_FOUND_USER("로그인된 사용자만 채팅에 참여할 수 있습니다.", HttpStatus.UNAUTHORIZED);


    private final String meassage;
    private final HttpStatus status;

    ChatErrorCode(String meassage, HttpStatus status) {
        this.meassage = meassage;
        this.status = status;
    }

    @Override
    public String defaultMessage() {
        return meassage;
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
