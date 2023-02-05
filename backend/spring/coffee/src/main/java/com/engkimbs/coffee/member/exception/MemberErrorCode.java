package com.engkimbs.coffee.member.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Getter
public enum MemberErrorCode {

    MEMBER_NOT_FOUND(BAD_REQUEST, "member is not founded")
    ;

    private final HttpStatus httpStatus;

    private final String detail;

    MemberErrorCode(HttpStatus httpStatus, String detail) {
        this.httpStatus = httpStatus;
        this.detail = detail;
    }
}
