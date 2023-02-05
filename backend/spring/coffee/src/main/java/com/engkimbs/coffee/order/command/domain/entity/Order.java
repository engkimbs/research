package com.engkimbs.coffee.order.command.domain.entity;

import com.engkimbs.coffee.common.converter.PointConverter;
import com.engkimbs.coffee.common.vo.Point;
import com.engkimbs.coffee.member.command.domain.vo.MemberId;
import com.engkimbs.coffee.menu.query.MenuData;
import com.engkimbs.coffee.order.api.OrderRequest;
import com.engkimbs.coffee.order.command.domain.vo.OrderLine;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Entity
@Table(name = "purchase_order")
@Getter
@Access(AccessType.FIELD)
@EqualsAndHashCode(of = {"orderNo"})
public class Order {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long orderNo;

    private MemberId orderer;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "order_line",
            indexes = {@Index(columnList = "order_date")},
            joinColumns = @JoinColumn(name = "order_number"))
    private List<OrderLine> orderLineList;

    @Convert(converter = PointConverter.class)
    @Column(name = "total_point")
    private Point totalPoint;

    @CreationTimestamp
//    @Column(name = "order_date", nullable = false, length = 20, updatable = false,
//            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @Column(name = "order_date", nullable = false, length = 20, updatable = false)
    private LocalDateTime orderDate;

    protected Order() {
    }

    @Builder
    protected Order(MemberId orderer, List<OrderLine> orderLineList, LocalDateTime localDateTime) {
        this.orderer = orderer;
        this.orderLineList = orderLineList;
        this.totalPoint = new Point(orderLineList.stream()
                .map(OrderLine::getTotalPrice)
                .mapToInt(Point::getValue).sum());
        this.orderDate = localDateTime == null ? LocalDateTime.now() : localDateTime;
    }

    public static Order from(OrderRequest orderRequest) {
        List<OrderLine> orderLines = orderRequest.getOrderLineList().stream()
                .map(OrderLine::from)
                .collect(Collectors.toList());;
        return Order.builder()
                .orderer(MemberId.of(orderRequest.getOrderer()))
                .orderLineList(orderLines)
                .build();
    }

    public static Order of(MemberId memberId, MenuData menuData) {
        List<OrderLine> orderLines = new ArrayList<>();
        orderLines.add(OrderLine.from(menuData));

        return Order.builder()
                .orderer(memberId)
                .orderLineList(orderLines)
                .build();
    }
}