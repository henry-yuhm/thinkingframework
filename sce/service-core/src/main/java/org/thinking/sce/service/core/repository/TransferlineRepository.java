package org.thinking.sce.service.core.repository;

import org.springframework.stereotype.Repository;
import org.thinking.sce.service.core.domain.common.Transferline;

@Repository
public interface TransferlineRepository extends DomainRepository<Transferline, Long> {
}