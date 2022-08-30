package com.acme.deliveryservice.service.implement;

import com.acme.deliveryservice.domain.Store;
import com.acme.deliveryservice.repository.StoreRepository;
import com.acme.deliveryservice.service.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl extends BaseServiceImpl<Store> implements StoreService {


    private final StoreRepository storeRepository;


    @Override
    public JpaRepository<Store, Long> getRepository() {
        return storeRepository;
    }


}
