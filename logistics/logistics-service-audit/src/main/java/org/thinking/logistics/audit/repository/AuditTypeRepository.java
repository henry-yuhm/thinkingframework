package org.thinking.logistics.audit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;
import org.thinking.logistics.audit.entity.AuditType;

@Repository
public interface AuditTypeRepository extends JpaRepository<AuditType, Long>, QueryDslPredicateExecutor<AuditType> {
    AuditType findByName(String name);
}