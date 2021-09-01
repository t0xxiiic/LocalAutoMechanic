package com.example.demo.services.base;

import org.springframework.data.domain.Page;

import java.util.List;

public interface BaseService<E, ID> {
    Page<E> fetchAllPaged(int page, int size);

    E create(E newEntity);

    E update(ID id, E newEntity);

    boolean delete(ID id);

    E findById(ID id);

    boolean existsById(ID id);

    boolean validEntity(E entity);
}
