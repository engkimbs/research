package com.engkimbs.coffee.order.command.app;

import com.engkimbs.coffee.common.event.Events;
import com.engkimbs.coffee.member.command.app.MemberService;
import com.engkimbs.coffee.member.command.domain.entity.Member;
import com.engkimbs.coffee.member.command.domain.vo.MemberId;
import com.engkimbs.coffee.menu.command.domain.menu.vo.MenuId;
import com.engkimbs.coffee.menu.query.MenuData;
import com.engkimbs.coffee.menu.query.MenuQueryService;
import com.engkimbs.coffee.order.api.OrderResponse;
import com.engkimbs.coffee.order.command.domain.entity.Order;
import com.engkimbs.coffee.order.command.domain.event.OrderEvent;
import com.engkimbs.coffee.order.command.domain.repository.OrderRepository;
import com.engkimbs.coffee.order.exception.OrderException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.engkimbs.coffee.order.exception.OrderErrorCode.INSUFFICIENT_POINT;

@Service
public class OrderService {

    OrderRepository orderRepository;

    MemberService memberService;

    MenuQueryService menuQueryService;

    public OrderService(OrderRepository orderRepository,
                        MemberService memberService,
                        MenuQueryService menuQueryService
                        ) {
        this.orderRepository = orderRepository;
        this.memberService = memberService;
        this.menuQueryService = menuQueryService;
    }

    @Transactional
    public OrderResponse placeOrder(MemberId memberId, MenuId menuId) {
        Member member = memberService.getMemberById(memberId);

        MenuData menuData = menuQueryService.getMenuDataByMenuId(menuId);

        if(!checkIfMemberPointSufficient(member, menuData))
            throw new OrderException(INSUFFICIENT_POINT, member.getId() + " is not sufficient");

        Member updatedMember = memberService.updateMemberPoint(member, menuData.getPrice());

        Order order = Order.of(memberId, menuData);
        Order savedOrder = orderRepository.save(order);

        sendOrderToEventStoreAsync(savedOrder);

        return OrderResponse.of(savedOrder, updatedMember.getPoint());
    }

    private void sendOrderToEventStoreAsync(Order savedOrder) {
        OrderEvent orderEvent = OrderEvent.from(savedOrder);
        Events.raise(orderEvent);
    }

    @Transactional
    public OrderResponse placeOrder(Order order) {
        Member member = memberService.getMemberById(order.getOrderer());
        if(!checkIfMemberPointSufficient(member, order))
            throw new OrderException(INSUFFICIENT_POINT, member.getId() + " is not sufficient");

        Member updatedMember = memberService.updateMemberPoint(member, order.getTotalPoint());

        Order savedOrder = orderRepository.save(order);

        sendOrderToEventStoreAsync(savedOrder);

        return OrderResponse.of(savedOrder, updatedMember.getPoint());
    }

    private boolean checkIfMemberPointSufficient(Member member, Order order) {
        return member.getPoint().getValue() > order.getTotalPoint().getValue();
    }

    private boolean checkIfMemberPointSufficient(Member member, MenuData menuData) {
        return member.getPoint().getValue() > menuData.getPrice().getValue();
    }
}