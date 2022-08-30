package com.acme.deliveryservice.service.service;

import com.acme.deliveryservice.domain.Customer;

import java.util.List;


public interface CustomerService extends BaseService<Customer> {

    List<Customer> getInactiveCustomers();

    List<Customer> getActiveCustomers();

}
