package com.acme.deliveryservice.service.implement;

import com.acme.deliveryservice.domain.Product;
import com.acme.deliveryservice.repository.ProductRepository;
import com.acme.deliveryservice.service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl extends BaseServiceImpl<Product> implements ProductService {

    private final ProductRepository productRepository;


    @Override
    public JpaRepository<Product, Long> getRepository() {
        return productRepository;
    }


    @Override
    public List<Product> getProductsInactive() {
        return productRepository.getProductsByIsInStockIsFalse();
    }

    @Override
    public List<Product> getProductsActive() {
        return productRepository.getProductsByIsInStockIsTrue();
    }
}
