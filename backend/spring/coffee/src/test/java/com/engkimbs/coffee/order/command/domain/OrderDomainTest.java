package com.engkimbs.coffee.order.command.domain;

import com.engkimbs.coffee.common.vo.Point;
import com.engkimbs.coffee.member.command.domain.vo.MemberId;
import com.engkimbs.coffee.menu.command.domain.menu.vo.MenuId;
import com.engkimbs.coffee.order.command.domain.entity.Order;
import com.engkimbs.coffee.order.command.domain.repository.OrderRepository;
import com.engkimbs.coffee.order.command.domain.vo.OrderLine;
import com.engkimbs.coffee.order.command.domain.vo.OrderNo;
import com.engkimbs.coffee.order.command.domain.vo.OrderState;
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
public class OrderDomainTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    @DisplayName("order entity 제대로 적재되는 지 확인")
    void saveOrderDomain() {
        orderRepository.deleteAll();

        MenuId menuId1 = MenuId.of(1L);
        MenuId menuId2 = MenuId.of(2L);
        MenuId menuId3 = MenuId.of(3L);
        MenuId menuId4 = MenuId.of(4L);

        OrderLine orderLine1 = OrderLine.builder()
                .menuId(menuId1)
                .price(Point.of(2000))
                .quantity(4)
                .build();

        OrderLine orderLine2 = OrderLine.builder()
                .menuId(menuId2)
                .price(Point.of(2200))
                .quantity(4)
                .build();

        OrderLine orderLine3 = OrderLine.builder()
                .menuId(menuId3)
                .price(Point.of(2200))
                .quantity(5)
                .build();

        OrderLine orderLine4 = OrderLine.builder()
                .menuId(menuId4)
                .price(Point.of(2200))
                .quantity(2)
                .build();

        OrderNo orderNo = OrderNo.of(1L);

        List<OrderLine> orderLineList = new ArrayList<>();
        orderLineList.add(orderLine1);
        orderLineList.add(orderLine2);
        orderLineList.add(orderLine3);
        orderLineList.add(orderLine4);

        Order order = Order.builder()
                .orderer(MemberId.of("010-3354-5349"))
                .orderLineList(orderLineList)
                .build();

        orderRepository.save(order);
    }
}
