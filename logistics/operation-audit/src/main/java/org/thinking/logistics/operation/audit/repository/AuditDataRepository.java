package org.thinking.logistics.operation.audit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import org.thinking.logistics.operation.audit.domain.AuditData;

@Repository
public interface AuditDataRepository extends JpaRepository<AuditData, Long>, QuerydslPredicateExecutor<AuditData> {
}