package com.example.demo.services.base;

import com.example.demo.models.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.UUID;

import static com.example.demo.utils.Constants.*;

@Service
public abstract class BaseServiceImpl<
        E extends BaseEntity,
        ID extends UUID,
        R extends JpaRepository<E, ID>> implements BaseService<E, ID> {

    protected final R repository;

    @Autowired
    private Validator validator;

    protected BaseServiceImpl(R repository) {
        this.repository = repository;
    }

    @Override
    public Page<E> fetchAllPaged(int page, int size) {
        return repository.findAll(PageRequest.of(page, size));
    }

    @Override
    public E create(@NotNull E newEntity) {
        validEntity(newEntity);
        return repository.save(newEntity);
    }

    @Override
    public E update(@NotNull ID id, @NotNull E newEntity) {
        newEntity.setId(id);
        validEntity(newEntity);
        return repository.save(newEntity);
    }

    @Override
    public boolean delete(@NotNull ID id) {
        try {
            repository.deleteById(id);
            return true;
        } catch (EmptyResultDataAccessException exception) {
            throw new EntityNotFoundException(String.format(ID_DOES_NOT_EXIST, id));
        }
    }

    @Override
    public E findById(@NotNull ID id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(ENTITY_NOT_FOUND_MSG, id)));
    }

    @Override
    public boolean existsById(@NotNull ID id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException(String.format(ENTITY_NOT_FOUND_MSG, id));
        }
        return true;
    }

    @Override
    public boolean validEntity(@NotNull E entity) {
        Set<ConstraintViolation<E>> constraintViolations = validator.validate(entity);

        if (!constraintViolations.isEmpty()) {
            StringBuilder errorMessages = new StringBuilder();
            for (ConstraintViolation<E> constraintViolation : constraintViolations) {
                errorMessages.append(constraintViolation.getMessage());
            }
            throw new ConstraintViolationException(VALIDATION_MESSAGE_PREFIX + errorMessages, constraintViolations);
        }
        return true;
    }
}
