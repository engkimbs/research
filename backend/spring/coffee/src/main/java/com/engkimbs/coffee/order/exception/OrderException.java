package com.engkimbs.coffee.order.exception;

import lombok.Getter;

@Getter
public class OrderException extends RuntimeException {

    private final OrderErrorCode errorCode;
    private final String errorMessage;

    public OrderException(OrderErrorCode errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}