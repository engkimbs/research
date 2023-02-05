package com.engkimbs.coffee.order.command.app;

import com.engkimbs.coffee.order.api.OrderLineRequest;
import com.engkimbs.coffee.order.api.OrderRequest;
import com.engkimbs.coffee.order.api.OrderResponse;
import com.engkimbs.coffee.order.command.domain.entity.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

@ActiveProfiles("test")
@Sql("classpath:init-test.sql")
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class OrderServiceTest {

    OrderService orderService;

    public OrderServiceTest(@Autowired OrderService orderService) {
        this.orderService = orderService;
    }

    @Test
    @DisplayName("제대로 주문이 되는 지 확인")
    void placeOrder() {
        OrderLineRequest orderLine1 = OrderLineRequest.builder()
                .menuId(1L)
                .price(2000)
                .quantity(4)
                .build();

        OrderLineRequest orderLine2 = OrderLineRequest.builder()
                .menuId(2L)
                .price(2200)
                .quantity(4)
                .build();

        List<OrderLineRequest> orderLineList = new ArrayList<>();
        orderLineList.add(orderLine1);
        orderLineList.add(orderLine2);

        OrderRequest orderRequest = OrderRequest.builder()
                .orderer("010-3354-5349")
                .orderLineList(orderLineList)
                .build();

        Order order = Order.from(orderRequest);

        OrderResponse orderResponse = orderService.placeOrder(order);
        System.out.println(orderRequest);
    }
}
