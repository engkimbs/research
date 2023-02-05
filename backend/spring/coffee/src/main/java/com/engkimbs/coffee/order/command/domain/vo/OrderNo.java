package com.engkimbs.coffee.order.command.domain.vo;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
@EqualsAndHashCode(of = "number")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class OrderNo implements Serializable {

    @Column(name="order_number")
    // @GeneratedValue(strategy= GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator", sequenceName = "ORDER_SEQ")
    private Long number;

    public OrderNo(Long number) {
        this.number = number;
    }

    public static OrderNo of(Long number) {
        return new OrderNo(number);
    }
}