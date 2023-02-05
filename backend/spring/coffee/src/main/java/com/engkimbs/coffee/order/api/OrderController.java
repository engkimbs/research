package com.engkimbs.coffee.order.api;

import com.engkimbs.coffee.member.command.domain.vo.MemberId;
import com.engkimbs.coffee.menu.command.domain.menu.vo.MenuId;
import com.engkimbs.coffee.order.command.app.OrderService;
import com.engkimbs.coffee.order.command.domain.entity.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/single_order")
    public ResponseEntity<OrderResponse> singleOrder(@RequestParam("member_id") String memberId,
                                                     @RequestParam("menu_id") Long menuId) {
        OrderResponse orderResponse = this.orderService.placeOrder(MemberId.of(memberId), MenuId.of(menuId));
        return ResponseEntity.status(HttpStatus.CREATED).body(orderResponse);
    }

    @PostMapping("/order")
    public ResponseEntity<OrderResponse> order(@Valid @RequestBody OrderRequest orderRequest) {
        OrderResponse orderResponse = this.orderService.placeOrder(Order.from(orderRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(orderResponse);
    }
}