package org.thinking.sce.service.core.repository.container;

import org.springframework.stereotype.Repository;
import org.thinking.sce.service.core.domain.container.Totebox;
import org.thinking.sce.service.core.repository.DomainRepository;

@Repository
public interface ToteboxRepository extends DomainRepository<Totebox, Long> {
}