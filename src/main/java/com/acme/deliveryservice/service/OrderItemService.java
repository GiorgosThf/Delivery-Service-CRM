package com.acme.deliveryservice.service;

import com.acme.deliveryservice.domain.OrderItem;
import com.acme.deliveryservice.repository.OrderItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;


    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public List<OrderItem> findOrderByOrderId(Long order_id) {
        return orderItemRepository.findOrderItemByOrderId(order_id);
    }


}

