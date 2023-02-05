package com.engkimbs.coffee.order.command.domain.event;

import com.engkimbs.coffee.common.event.Event;
import com.engkimbs.coffee.common.vo.Point;
import com.engkimbs.coffee.member.command.domain.vo.MemberId;
import com.engkimbs.coffee.menu.command.domain.menu.vo.MenuId;
import com.engkimbs.coffee.order.command.domain.entity.Order;
import com.engkimbs.coffee.order.command.domain.vo.OrderLine;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class OrderEvent extends Event {

    private MemberId memberId;

    private List<MenuId> menuIdList;

    private Point payment;

    @Builder
    public OrderEvent(MemberId memberId, List<MenuId> menuIdList, Point payment) {
        this.memberId = memberId;
        this.menuIdList = menuIdList;
        this.payment = payment;
    }

    public static OrderEvent from(Order order) {
        List<MenuId> menuIdList = order.getOrderLineList().stream()
                .map(OrderLine::getMenuId).toList();

        return OrderEvent.builder()
                .memberId(order.getOrderer())
                .menuIdList(menuIdList)
                .payment(order.getTotalPoint())
                .build();
    }
}
