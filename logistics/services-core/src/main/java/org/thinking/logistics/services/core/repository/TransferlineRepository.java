package org.thinking.logistics.services.core.repository;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.common.Transferline;

@Repository
public interface TransferlineRepository extends DomainRepository<Transferline, Long> {
}