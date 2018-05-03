package org.thinking.logistics.services.core.service;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.Data;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.thinking.logistics.services.core.repository.EntityRepository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Data
public abstract class EntityService<Q extends EntityPathBase<E>, E, ID> implements EntityRepository<E, ID> {
    private final Set<Predicate> predicates = new LinkedHashSet<>();

    private JPAQueryFactory queryFactory;

    private Q path;

    @Resource
    @PersistenceContext
    private EntityManager entityManager;

    @Resource
    private JpaRepository<E, ID> repository;

    public EntityService(JpaRepository<E, ID> repository) {
        this.queryFactory = new JPAQueryFactory(this.entityManager);
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

    @Override
    public E getOne() {
        return this.queryFactory.selectFrom(this.path)
            .where((Predicate[]) this.predicates.toArray())
            .fetchOne();
    }
}