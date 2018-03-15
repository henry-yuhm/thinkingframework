package org.jointown.logistics.audit.repository;

import org.jointown.logistics.audit.entity.AuditType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditTypeRepository extends JpaRepository<AuditType, Long>, QueryDslPredicateExecutor<AuditType> {
    AuditType findByName(String name);
}