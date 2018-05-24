package org.thinking.logistics.services.core.repository.container;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.container.Totebox;
import org.thinking.logistics.services.core.repository.DomainRepository;

@Repository
public interface ToteboxRepository extends DomainRepository<Totebox, Long> {
}