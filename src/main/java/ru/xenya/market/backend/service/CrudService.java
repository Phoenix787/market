package ru.xenya.market.backend.service;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface CrudService<T> {
    JpaRepository<T, Long> getRepository();

    default T save(T entity){
        return getRepository().saveAndFlush(entity);
    }

    default void delete(T entity) {
        if (entity == null) {
            throw new EntityNotFoundException();
        }
        getRepository().delete(entity);
    }

    default List<T> findAll(){
        return getRepository().findAll();
    }
}
