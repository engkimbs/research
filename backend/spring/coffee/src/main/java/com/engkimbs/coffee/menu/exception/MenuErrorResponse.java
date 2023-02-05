package com.engkimbs.coffee.menu.exception;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Getter
public class MenuErrorResponse {

    private final LocalDateTime timestamp = LocalDateTime.now();
    private final int status;
    private final String error;
    private final String code;
    private final String message;

    @Builder
    public MenuErrorResponse(int status, String error, String code, String message) {
        this.status = status;
        this.error = error;
        this.code = code;
        this.message = message;
    }

    public static ResponseEntity<MenuErrorResponse> toResponseEntity(MenuException menuException) {
        MenuErrorCode errorCode = menuException.getErrorCode();
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(MenuErrorResponse.builder()
                        .status(errorCode.getHttpStatus().value())
                        .error(errorCode.getHttpStatus().name())
                        .code(errorCode.name())
                        .message(menuException.getErrorMessage())
                        .build()
                );
    }
}

