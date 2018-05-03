package org.thinking.logistics.services.core.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface EntityRepository<E, ID> {
    Optional<E> findOne(Example<E> example);

    List<E> findAll(Example<E> example);

    E save(E entity);

    List<E> saveAll(List<E> entities);

    E getOne();
}