package com.engkimbs.coffee.menu.query;

import com.engkimbs.coffee.common.vo.Point;
import com.engkimbs.coffee.member.command.domain.vo.MemberId;
import com.engkimbs.coffee.menu.command.domain.menu.entity.Menu;
import com.engkimbs.coffee.menu.command.domain.menu.repository.MenuRepository;
import com.engkimbs.coffee.menu.command.domain.menu.vo.MenuId;
import com.engkimbs.coffee.order.command.domain.entity.Order;
import com.engkimbs.coffee.order.command.domain.repository.OrderRepository;
import com.engkimbs.coffee.order.command.domain.vo.OrderLine;
import com.engkimbs.coffee.order.command.domain.vo.OrderNo;
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
import java.util.Optional;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("test")
@Sql("classpath:init-test.sql")
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MenuQueryServiceTest {

    @Autowired
    private MenuQueryService menuQueryService;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    @DisplayName("service단에서 popular 메뉴 데이터 찾아오는 지 테스트")
    void getMenuDataSummary() {
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

        List<MenuDataSummary> menuDataSummaryList = menuQueryService.getTopNBestMenuListInLastMDays(3, 7);
        List<Long> menuIdListInOrder = menuDataSummaryList.stream().map(MenuDataSummary::getMenuId).toList();

        assertThat(menuIdListInOrder, contains(3L, 1L, 2L));
    }
}
