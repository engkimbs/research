package com.engkimbs.springjpamybatis.member.exception;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Getter
public class MemberErrorResponse {

    private final LocalDateTime timestamp = LocalDateTime.now();
    private final int status;
    private final String error;
    private final String code;
    private final String message;

    @Builder
    public MemberErrorResponse(int status, String error, String code, String message) {
        this.status = status;
        this.error = error;
        this.code = code;
        this.message = message;
    }

    public static ResponseEntity<MemberErrorResponse> toResponseEntity(MemberException memberException) {
        MemberErrorCode errorCode = memberException.getErrorCode();
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(MemberErrorResponse.builder()
                        .status(errorCode.getHttpStatus().value())
                        .error(errorCode.getHttpStatus().name())
                        .code(errorCode.name())
                        .message(memberException.getErrorMessage())
                        .build())
                ;
    }
}
