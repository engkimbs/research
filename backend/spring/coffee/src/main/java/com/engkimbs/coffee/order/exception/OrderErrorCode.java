package com.engkimbs.coffee.order.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Getter
public enum OrderErrorCode {

    INSUFFICIENT_POINT(BAD_REQUEST)
    ;

    private final HttpStatus httpStatus;

    OrderErrorCode(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}