package org.thinking.sce.service.core.service.container;

import org.springframework.stereotype.Service;
import org.thinking.sce.service.core.domain.container.Pallet;
import org.thinking.sce.service.core.domain.container.QPallet;
import org.thinking.sce.service.core.repository.DomainRepository;
import org.thinking.sce.service.core.service.DomainService;

import javax.persistence.EntityManager;

@Service
public class PalletService extends DomainService<QPallet, Pallet, Long> {
    public PalletService(EntityManager entityManager, DomainRepository<Pallet, Long> repository) {
        super(entityManager, repository, QPallet.pallet);
    }
}