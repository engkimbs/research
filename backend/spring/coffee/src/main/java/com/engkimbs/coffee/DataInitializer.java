package com.engkimbs.coffee;

import com.engkimbs.coffee.common.vo.Point;
import com.engkimbs.coffee.member.command.domain.entity.Member;
import com.engkimbs.coffee.member.command.domain.repository.MemberRepository;
import com.engkimbs.coffee.member.command.domain.vo.MemberId;
import com.engkimbs.coffee.menu.command.domain.menu.entity.Menu;
import com.engkimbs.coffee.menu.command.domain.menu.repository.MenuRepository;
import com.engkimbs.coffee.menu.command.domain.menu.vo.MenuId;
import com.engkimbs.coffee.order.command.domain.entity.Order;
import com.engkimbs.coffee.order.command.domain.repository.OrderRepository;
import com.engkimbs.coffee.order.command.domain.vo.OrderLine;
import com.engkimbs.coffee.order.command.domain.vo.OrderNo;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Profile("!prod")
@Component
public class DataInitializer implements ApplicationRunner {

    MenuRepository menuRepository;

    OrderRepository orderRepository;

    MemberRepository memberRepository;

    public DataInitializer(MenuRepository menuRepository,
                           OrderRepository orderRepository,
                           MemberRepository memberRepository
                           ) {
        this.menuRepository = menuRepository;
        this.orderRepository = orderRepository;
        this.memberRepository = memberRepository;
    }
    @Override
    public void run(ApplicationArguments args) {
        MenuId menuId1 = MenuId.of(1L);
        MenuId menuId2 = MenuId.of(2L);
        MenuId menuId3 = MenuId.of(3L);
        MenuId menuId4 = MenuId.of(4L);

        Menu menu1 = Menu.builder()
                .id(menuId1)
                .menuName("americano")
                .price(Point.of(3500))
                .build();
        Menu menu2 = Menu.builder()
                .id(menuId2)
                .menuName("ice americano")
                .price(Point.of(4000))
                .build();
        Menu menu3 = Menu.builder()
                .id(menuId3)
                .menuName("latte")
                .price(Point.of(4200))
                .build();
        Menu menu4 = Menu.builder()
                .id(menuId4)
                .menuName("ice tea")
                .price(Point.of(3000))
                .build();

        menuRepository.saveAll(Arrays.asList(
                menu1,
                menu2,
                menu3,
                menu4
        ));

        Member member1 = Member.builder()
                .id(MemberId.of("010-3354-5349"))
                .name("daniel kim")
                .point(Point.of(145000))
                .build();

        Member member2 = Member.builder()
                .id(MemberId.of("010-9195-9531"))
                .name("crystal yun")
                .point(Point.of(95000))
                .build();

        Member member3 = Member.builder()
                .id(MemberId.of("010-2725-5349"))
                .name("kay yun")
                .point(Point.of(90000))
                .build();

        memberRepository.saveAll(Arrays.asList(member1, member2, member3));

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime oneMonthAgo = now.minusDays(20);

        for(int i=0; i<40; ++i) {
            LocalDateTime localDate = oneMonthAgo.plusDays(i);

            OrderLine orderLine1 = OrderLine.builder()
                    .menuId(menu1.getId())
                    .price(menu1.getPrice())
                    .quantity(4)
                    .localDateTime(localDate)
                    .build();

            OrderLine orderLine2 = OrderLine.builder()
                    .menuId(menu2.getId())
                    .price(menu2.getPrice())
                    .quantity(2)
                    .localDateTime(localDate)
                    .build();

            OrderLine orderLine3 = OrderLine.builder()
                    .menuId(menu3.getId())
                    .price(menu3.getPrice())
                    .quantity(5)
                    .localDateTime(localDate)
                    .build();

            OrderLine orderLine4 = OrderLine.builder()
                    .menuId(menu4.getId())
                    .price(menu4.getPrice())
                    .quantity(2)
                    .localDateTime(localDate)
                    .build();

            List<OrderLine> orderLineList = Arrays.asList(
                    orderLine1,
                    orderLine2,
                    orderLine3,
                    orderLine4
            );

            OrderLine orderLine5 = OrderLine.builder()
                    .menuId(menu3.getId())
                    .price(menu3.getPrice())
                    .quantity(5)
                    .localDateTime(localDate)
                    .build();

            List<OrderLine> orderLineList2 = Arrays.asList(
                    orderLine1,
                    orderLine2,
                    orderLine3,
                    orderLine4,
                    orderLine5
            );

            OrderLine orderLine6 = OrderLine.builder()
                    .menuId(menu4.getId())
                    .price(menu4.getPrice())
                    .quantity(3)
                    .localDateTime(localDate)
                    .build();

            List<OrderLine> orderLineList3 = Arrays.asList(
                    orderLine1,
                    orderLine2,
                    orderLine3,
                    orderLine4,
                    orderLine6
            );

            Order order1 = Order.builder()
                    .orderer(MemberId.of("010-3354-5349"))
                    .orderLineList(orderLineList)
                    .localDateTime(localDate)
                    .build();

            Order order2 = Order.builder()
                    .orderer(MemberId.of("010-9195-9531"))
                    .orderLineList(orderLineList2)
                    .localDateTime(localDate)
                    .build();

            Order order3 = Order.builder()
                    .orderer(MemberId.of("010-2725-5349"))
                    .orderLineList(orderLineList3)
                    .localDateTime(localDate)
                    .build();

            orderRepository.saveAll(Arrays.asList(order1, order2, order3));
        }
    }
}
