package com.acme.deliveryservice.service.implement;

import com.acme.deliveryservice.domain.BaseModel;
import com.acme.deliveryservice.service.service.BaseService;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Transactional
public abstract class BaseServiceImpl<T extends BaseModel> implements BaseService<T> {

    public abstract JpaRepository<T, Long> getRepository();

    @Override
    public T create(final T entity) {
        return getRepository().save(entity);
    }

    @Override
    public void update(final T entity) {
        getRepository().save(entity);
    }

    @Override
    public void delete(final T entity) {
        getRepository().delete(entity);
    }

    @Override
    public void deleteById(final Long id) {
        getRepository().deleteById(id);
    }

    @Override
    public boolean exists(final T entity) {
        return getRepository().existsById(entity.getId());
    }

    @Override
    public T get(final Long id) {
        return getRepository().findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<T> findAll() {
        return getRepository().findAll();
    }


}