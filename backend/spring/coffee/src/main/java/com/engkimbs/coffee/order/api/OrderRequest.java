package com.engkimbs.coffee.order.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@ToString
public class OrderRequest {

    @NotEmpty
    private String orderer;

    @Valid
    @NotEmpty
    @JsonProperty("order_line_list")
    private List<OrderLineRequest> orderLineList;

    @Builder
    public OrderRequest(String orderer, List<OrderLineRequest> orderLineList) {
        this.orderer = orderer;
        this.orderLineList = orderLineList;
    }
}