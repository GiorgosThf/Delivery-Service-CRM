package com.acme.deliveryservice.service.service;

import com.acme.deliveryservice.domain.BaseModel;

import java.util.List;


public interface BaseService<T extends BaseModel> {

    T create(final T entity);

    void update(T entity);

    void delete(T entity);

    void deleteById(Long id);

    boolean exists(T entity);

    T get(Long id);

    List<T> findAll();


}