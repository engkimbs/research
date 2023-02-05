package com.engkimbs.coffee.common.exception;

import com.engkimbs.coffee.member.exception.MemberErrorResponse;
import com.engkimbs.coffee.member.exception.MemberException;
import com.engkimbs.coffee.menu.exception.MenuErrorResponse;
import com.engkimbs.coffee.menu.exception.MenuException;
import com.engkimbs.coffee.order.exception.OrderErrorResponse;
import com.engkimbs.coffee.order.exception.OrderException;
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

    @ExceptionHandler(OrderException.class)
    public ResponseEntity<OrderErrorResponse> exception(OrderException exception) {
        log.error("order domain throw exception : {}, message : {}", exception.getErrorCode(), exception.getErrorMessage());
        return OrderErrorResponse.toResponseEntity(exception);
    }

    @ExceptionHandler(MenuException.class)
    public ResponseEntity<MenuErrorResponse> exception(MenuException exception) {
        log.error("menu domain throw exception : {}, message : {}", exception.getErrorCode(), exception.getErrorMessage());
        return MenuErrorResponse.toResponseEntity(exception);
    }
}