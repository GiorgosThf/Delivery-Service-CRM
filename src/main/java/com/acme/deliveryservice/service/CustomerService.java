package com.acme.deliveryservice.service;

import com.acme.deliveryservice.domain.Customer;
import com.acme.deliveryservice.repository.CustomerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;


    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public Page<Customer> getPage(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    public List<Customer> getActiveCustomers() {
        return customerRepository.findAllByActiveIsTrue();
    }


    public List<Customer> getInactiveCustomers() {
        return customerRepository.findAllByActiveIsFalse();
    }

}