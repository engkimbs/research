package com.engkimbs.springjpamybatis.member.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
public enum MemberErrorCode {

    FAILED_TO_FOUND_MEMBER(NOT_FOUND, "member is not founded"),
    FAILED_TO_ADD_MEMBER(BAD_REQUEST, "failed to add member");

    private final HttpStatus httpStatus;

    private final String detail;

    MemberErrorCode(HttpStatus httpStatus, String detail) {
        this.httpStatus = httpStatus;
        this.detail = detail;
    }
}
