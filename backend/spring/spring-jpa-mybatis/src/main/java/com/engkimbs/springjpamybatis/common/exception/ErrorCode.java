package com.engkimbs.springjpamybatis.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    METHOD_NOT_FOUND(NOT_FOUND, "요청을 처리할 메서드를 찾을 수 없습니다.")
    ;

    private final HttpStatus httpStatus;

    private final String detail;
}