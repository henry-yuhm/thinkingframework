package org.thinking.logistics.services.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DomainRepository<E, ID> extends JpaRepository<E, ID>, QuerydslPredicateExecutor<E> {
}