package com.acme.deliveryservice.service.implement;

import com.acme.deliveryservice.domain.Order;
import com.acme.deliveryservice.repository.OrderRepository;
import com.acme.deliveryservice.service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl extends BaseServiceImpl<Order> implements OrderService {

    private final OrderRepository orderRepository;


    @Override
    public JpaRepository<Order, Long> getRepository() {
        return orderRepository;
    }

    @Override
    public List<Order> getCompletedOrders() {
        return orderRepository.findAllByStatusIsTrue();
    }

    @Override
    public List<Order> getInCompletedOrders() {
        return orderRepository.findAllByStatusIsFalse();
    }
}
