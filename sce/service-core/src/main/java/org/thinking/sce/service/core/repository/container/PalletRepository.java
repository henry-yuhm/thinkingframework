package org.thinking.sce.service.core.repository.container;

import org.springframework.stereotype.Repository;
import org.thinking.sce.service.core.domain.container.Pallet;
import org.thinking.sce.service.core.repository.DomainRepository;

@Repository
public interface PalletRepository extends DomainRepository<Pallet, Long> {
}