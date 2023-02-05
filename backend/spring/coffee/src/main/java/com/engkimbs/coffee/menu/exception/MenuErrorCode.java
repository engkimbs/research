package com.engkimbs.coffee.menu.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Getter
public enum MenuErrorCode {

    MENU_NOT_FOUND(BAD_REQUEST)
    ;

    private final HttpStatus httpStatus;

    MenuErrorCode(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
