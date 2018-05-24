package org.thinking.logistics.services.core.service.container;

import org.springframework.stereotype.Service;
import org.thinking.logistics.services.core.domain.container.QTotebox;
import org.thinking.logistics.services.core.domain.container.Totebox;
import org.thinking.logistics.services.core.repository.DomainRepository;
import org.thinking.logistics.services.core.service.DomainService;

import javax.persistence.EntityManager;

@Service
public class ToteboxService extends DomainService<QTotebox, Totebox, Long> {
    public ToteboxService(EntityManager entityManager, DomainRepository<Totebox, Long> repository) {
        super(entityManager, repository, QTotebox.totebox);
    }
}