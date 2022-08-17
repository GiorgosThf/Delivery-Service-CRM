package com.acme.deliveryservice.service;

import com.acme.deliveryservice.domain.Order;
import com.acme.deliveryservice.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getCompletedOrders() {
        return orderRepository.findAllByStatusIsTrue();
    }

    public List<Order> getInCompletedOrders() {
        return orderRepository.findAllByStatusIsFalse();
    }
}
