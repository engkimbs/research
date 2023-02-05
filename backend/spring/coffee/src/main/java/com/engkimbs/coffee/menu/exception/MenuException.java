package com.engkimbs.coffee.menu.exception;

import lombok.Getter;

@Getter
public class MenuException extends RuntimeException {

    private final MenuErrorCode errorCode;
    private final String errorMessage;

    public MenuException(MenuErrorCode menuErrorCode, String errorMessage) {
        this.errorCode = menuErrorCode;
        this.errorMessage = errorMessage;
    }
}