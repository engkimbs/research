package com.engkimbs.coffee.order.command.domain.repository;

import com.engkimbs.coffee.order.command.domain.entity.Order;
import com.engkimbs.coffee.order.command.domain.vo.OrderNo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, OrderNo> {
}