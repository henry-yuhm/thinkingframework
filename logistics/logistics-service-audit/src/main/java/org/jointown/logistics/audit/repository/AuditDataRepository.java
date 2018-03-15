package org.jointown.logistics.audit.repository;

import org.jointown.logistics.audit.entity.AuditData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditDataRepository extends JpaRepository<AuditData, Long>, QueryDslPredicateExecutor<AuditData> {
}