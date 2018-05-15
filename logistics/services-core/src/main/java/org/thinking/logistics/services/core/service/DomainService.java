package org.thinking.logistics.services.core.service;

import com.querydsl.core.types.EntityPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.Data;
import org.thinking.logistics.services.core.repository.DomainRepository;

import javax.persistence.EntityManager;

@Data
public abstract class DomainService<P extends EntityPath<E>, E, ID> {
    private JPAQueryFactory factory;

    private DomainRepository<E, ID> repository;

    private P path;

    public DomainService(EntityManager entityManager, DomainRepository<E, ID> repository, P path) {
        this.factory = new JPAQueryFactory(entityManager);
        this.repository = repository;
        this.path = path;
    }
}