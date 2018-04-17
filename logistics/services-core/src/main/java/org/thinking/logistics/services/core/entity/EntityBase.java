package org.thinking.logistics.services.core.entity;

import lombok.Data;
import org.springframework.data.domain.Example;
import org.thinking.logistics.services.core.domain.CompositeException;
import org.thinking.logistics.services.core.domain.support.NotExistsEntityException;
import org.thinking.logistics.services.core.service.EntityService;

import javax.annotation.Resource;
import java.util.Optional;

@Data
public abstract class EntityBase<E, ID> {
    @Resource
    private EntityService<E, ID> service;

    public void verify(E probe) throws Exception {
    }

    public final E getOne(E probe, NotExistsEntityException ex) throws Exception {
        this.verify(probe);

        Optional<E> optional = this.service.findOne(Example.of(probe));

        if (!optional.isPresent()) {
            throw CompositeException.getException(ex.name());
        } else {
            return optional.get();
        }
    }
}