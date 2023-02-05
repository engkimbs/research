package com.engkimbs.coffee.order.command.domain.vo;

import com.engkimbs.coffee.common.converter.PointConverter;
import com.engkimbs.coffee.common.vo.Point;
import com.engkimbs.coffee.menu.command.domain.menu.vo.MenuId;
import com.engkimbs.coffee.menu.query.MenuData;
import com.engkimbs.coffee.order.api.OrderLineRequest;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class OrderLine {

    @Embedded
    private MenuId menuId;

    @Convert(converter = PointConverter.class)
    private Point price;

    private Integer quantity;

    @Convert(converter = PointConverter.class)
    @Column(name="total_price")
    private Point totalPrice;

    @CreationTimestamp
//    @Column(name = "order_date", nullable = false, length = 20, updatable = false,
//            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @Column(name = "order_date", nullable = false, length = 20, updatable = false)
    private LocalDateTime orderDate;

    @Builder
    public OrderLine(MenuId menuId, Point price, Integer quantity, LocalDateTime localDateTime) {
        this.menuId = menuId;
        this.price = price;
        this.quantity = quantity;
        this.totalPrice = calculateAmount();
        this.orderDate = localDateTime == null ? LocalDateTime.now() : localDateTime;
    }

    private Point calculateAmount() {
        return price.multiply(quantity);
    }

    public static OrderLine from(OrderLineRequest orderLineRequest) {
        return OrderLine.builder()
                .menuId(MenuId.of(orderLineRequest.getMenuId()))
                .price(Point.of(orderLineRequest.getPrice()))
                .quantity(orderLineRequest.getQuantity())
                .build();
    }

    public static OrderLine from(MenuData menuData) {
        return OrderLine.builder()
                .menuId(menuData.getMenuId())
                .price(menuData.getPrice())
                .quantity(1)
                .build();
    }
}