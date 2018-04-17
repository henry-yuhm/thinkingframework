package org.thinking.logistics.services.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.thinking.logistics.services.core.repository.EntityRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EntityService<E, ID> implements EntityRepository<E, ID> {
    private JpaRepository<E, ID> repository;

    @Autowired
    public EntityService(JpaRepository<E, ID> repository) {
        this.repository = repository;
    }

    @Override
    public Optional<E> findOne(Example<E> example) {
        return this.repository.findOne(example);
    }

    @Override
    public List<E> findAll(Example<E> example) {
        return this.repository.findAll(example);
    }

    @Override
    public E save(E entity) {
        return this.repository.save(entity);
    }

    @Override
    public List<E> saveAll(List<E> entities) {
        return this.repository.saveAll(entities);
    }
}