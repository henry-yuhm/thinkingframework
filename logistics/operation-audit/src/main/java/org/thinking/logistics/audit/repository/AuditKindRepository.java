package org.thinking.logistics.audit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import org.thinking.logistics.audit.entity.AuditKind;

@Repository
public interface AuditKindRepository extends JpaRepository<AuditKind, Long>, QuerydslPredicateExecutor<AuditKind> {
    AuditKind findByName(String name);
}