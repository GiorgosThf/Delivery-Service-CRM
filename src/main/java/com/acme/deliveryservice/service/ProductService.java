package com.acme.deliveryservice.service;

import com.acme.deliveryservice.domain.Product;
import com.acme.deliveryservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProductsActive() {

        return productRepository.findAllByIsInStockIsTrueOrderById();
    }

    public List<Product> getProductsInactive() {
        return productRepository.getProductsByIsInStockIsFalseOrderById();
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }
}
