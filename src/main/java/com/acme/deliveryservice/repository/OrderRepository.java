package com.acme.deliveryservice.repository;

import com.acme.deliveryservice.domain.Customer;
import com.acme.deliveryservice.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByStatusIsTrue();

    List<Order> findAllByStatusIsFalse();

    Optional<Object> findOrderByCustomer(Customer customer);

}
