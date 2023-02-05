package com.engkimbs.springjpamybatis.common.exception;

import com.engkimbs.springjpamybatis.member.exception.MemberErrorResponse;
import com.engkimbs.springjpamybatis.member.exception.MemberException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorResponse> exception() {
        log.error("there is no method for processing this request");
        return ErrorResponse.toResponseEntity(ErrorCode.METHOD_NOT_FOUND);
    }

    @ExceptionHandler(MemberException.class)
    public ResponseEntity<MemberErrorResponse> exception(MemberException exception) {
        log.error("member domain throw exception : {}, message : {}", exception.getErrorCode(), exception.getErrorMessage());
        return MemberErrorResponse.toResponseEntity(exception);
    }
}