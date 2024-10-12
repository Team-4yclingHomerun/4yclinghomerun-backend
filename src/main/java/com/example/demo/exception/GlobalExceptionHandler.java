package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * packageName    : com.example.demo.exception
 * fileName       : GlobalExceptionHandler
 * author         : JAEIK
 * date           : 10/12/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/12/24        JAEIK       최초 생성
 */
public final class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiResponseError> handleException(CustomException exception) {
        HttpStatus httpStatus = exception
                .getErrorCode()
                .defaultHttpStatus();
        ApiResponseError responseError = ApiResponseError.of(exception);

        return ResponseEntity
                .status(httpStatus)
                .body(responseError);
    }
}
