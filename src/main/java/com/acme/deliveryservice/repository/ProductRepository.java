package com.acme.deliveryservice.repository;

import com.acme.deliveryservice.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByIsInStockIsTrueOrderById();

    List<Product> getProductsByIsInStockIsFalseOrderById();

}
