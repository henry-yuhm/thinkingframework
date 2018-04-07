package org.thinking.logistics.audit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;
import org.thinking.logistics.audit.entity.AuditData;

@Repository
public interface AuditDataRepository extends JpaRepository<AuditData, Long>, QueryDslPredicateExecutor<AuditData> {
}