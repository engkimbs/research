package com.engkimbs.coffee.order.exception;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Getter
public class OrderErrorResponse {

    private final LocalDateTime timestamp = LocalDateTime.now();
    private final int status;
    private final String error;
    private final String code;
    private final String message;

    @Builder
    public OrderErrorResponse(int status, String error, String code, String message) {
        this.status = status;
        this.error = error;
        this.code = code;
        this.message = message;
    }

    public static ResponseEntity<OrderErrorResponse> toResponseEntity(OrderException orderException) {
        OrderErrorCode errorCode = orderException.getErrorCode();
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(OrderErrorResponse.builder()
                        .status(errorCode.getHttpStatus().value())
                        .error(errorCode.getHttpStatus().name())
                        .code(errorCode.name())
                        .message(orderException.getErrorMessage())
                        .build()
                );
    }
}

