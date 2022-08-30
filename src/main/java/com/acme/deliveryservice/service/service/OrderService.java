package com.acme.deliveryservice.service.service;

import com.acme.deliveryservice.domain.Order;

import java.util.List;

public interface OrderService extends BaseService<Order> {
    List<Order> getCompletedOrders();

    List<Order> getInCompletedOrders();
}
