package com.acme.deliveryservice.repository;

import com.acme.deliveryservice.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {


    List<Customer> findAllByActiveIsTrue();

    List<Customer> findAllByActiveIsFalse();

}
