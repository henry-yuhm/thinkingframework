package org.thinking.logistics.services.core.service.container;

import org.springframework.stereotype.Service;
import org.thinking.logistics.services.core.domain.container.Pallet;
import org.thinking.logistics.services.core.domain.container.QPallet;
import org.thinking.logistics.services.core.repository.DomainRepository;
import org.thinking.logistics.services.core.service.DomainService;

import javax.persistence.EntityManager;

@Service
public class PalletService extends DomainService<QPallet, Pallet, Long> {
    public PalletService(EntityManager entityManager, DomainRepository<Pallet, Long> repository) {
        super(entityManager, repository, QPallet.pallet);
    }
}