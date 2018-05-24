package org.thinking.logistics.services.core.repository.container;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.container.Pallet;
import org.thinking.logistics.services.core.repository.DomainRepository;

@Repository
public interface PalletRepository extends DomainRepository<Pallet, Long> {
}