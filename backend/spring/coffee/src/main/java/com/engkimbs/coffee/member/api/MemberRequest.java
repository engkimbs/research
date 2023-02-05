package com.engkimbs.coffee.member.api;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@ToString
@EqualsAndHashCode(of = {"id"})
public class MemberRequest {

    @NotEmpty
    @Pattern(regexp = PHONE_NUMBER_REGEX)
    private final String id;

    private final String name;

    @NotNull
    @Min(0)
    private final Integer money;

    @Builder
    public MemberRequest(String id, String name, Integer money) {
        this.id = id;
        this.name = name;
        this.money = money;
    }

    public static final String PHONE_NUMBER_REGEX =
            "^01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$";
}