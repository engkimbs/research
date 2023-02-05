package com.engkimbs.coffee.member.command.domain.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(of="value")
@ToString
public class Money {

    private final int value;

    public Money(int value) {
        this.value = value;
    }

    public static Money of(int value) {
        return new Money(value);
    }

    public Money multiply(Integer quantity) {
        return new Money(value*quantity);
    }

    public Integer getValue() {
        return value;
    }
}