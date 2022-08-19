package com.acme.deliveryservice.repository;

import com.acme.deliveryservice.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long>, PagingAndSortingRepository<Store, Long> {

    //   Store findBySku(String sku);
}
