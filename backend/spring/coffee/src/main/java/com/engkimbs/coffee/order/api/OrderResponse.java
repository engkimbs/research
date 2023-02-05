package com.engkimbs.coffee.order.api;

import com.engkimbs.coffee.common.vo.Point;
import com.engkimbs.coffee.member.command.domain.entity.Member;
import com.engkimbs.coffee.order.command.domain.entity.Order;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class OrderResponse {

    @JsonProperty("order_no")
    private Long orderNo;

    private String orderer;

    private Integer payment;

    @JsonProperty("remaining_points")
    private Integer remainingPoints;

    @Builder
    public OrderResponse(Long orderNo, String orderer, Integer payment, Integer remainingPoints) {
        this.orderNo = orderNo;
        this.orderer = orderer;
        this.payment = payment;
        this.remainingPoints = remainingPoints;
    }

    public static OrderResponse of(Order order, Point remainingPoint) {
        return OrderResponse.builder()
                .orderNo(order.getOrderNo())
                .orderer(order.getOrderer().getId())
                .payment(order.getTotalPoint().getValue())
                .remainingPoints(remainingPoint.getValue())
                .build();
    }
}