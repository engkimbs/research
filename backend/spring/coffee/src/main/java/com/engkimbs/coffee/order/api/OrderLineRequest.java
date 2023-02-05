package com.engkimbs.coffee.order.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@ToString
public class OrderLineRequest {

    @NotNull
    @JsonProperty("menu_id")
    private Long menuId;

    @NotNull
    @Min(0)
    private Integer price;

    @NotNull
    @Min(0)
    private Integer quantity;

    @Builder
    public OrderLineRequest(Long menuId, Integer price, Integer quantity) {
        this.menuId = menuId;
        this.price = price;
        this.quantity = quantity;
    }
}