package com.acme.deliveryservice.service.service;

import com.acme.deliveryservice.domain.Product;

import java.util.List;

public interface ProductService extends BaseService<Product> {
    List<Product> getProductsInactive();

    List<Product> getProductsActive();
}
