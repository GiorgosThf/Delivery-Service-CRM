package com.acme.deliveryservice.service.implement;

import com.acme.deliveryservice.domain.Customer;
import com.acme.deliveryservice.repository.CustomerRepository;
import com.acme.deliveryservice.service.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl extends BaseServiceImpl<Customer> implements CustomerService {

    private final CustomerRepository customerRepository;


    @Override
    public JpaRepository<Customer, Long> getRepository() {
        return customerRepository;
    }


    @Override
    public List<Customer> getInactiveCustomers() {
        return customerRepository.findAllByActiveIsFalse();
    }

    @Override
    public List<Customer> getActiveCustomers() {
        return customerRepository.findAllByActiveIsTrue();
    }
}
