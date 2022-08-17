package com.acme.deliveryservice.repository;

import com.acme.deliveryservice.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {


    List<OrderItem> findOrderItemByOrderId(Long order_id);
}
