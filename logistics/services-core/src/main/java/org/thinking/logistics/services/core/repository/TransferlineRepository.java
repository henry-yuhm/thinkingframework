package org.thinking.logistics.services.core.repository;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.core.Transferline;

@Repository
public interface TransferlineRepository extends DomainRepository<Transferline, Long> {
}