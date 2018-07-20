package org.thinking.sce.operation.auditor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import org.thinking.sce.operation.auditor.domain.AuditData;

@Repository
public interface AuditDataRepository extends JpaRepository<AuditData, Long>, QuerydslPredicateExecutor<AuditData> {
}