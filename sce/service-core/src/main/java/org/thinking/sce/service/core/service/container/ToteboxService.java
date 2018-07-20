package org.thinking.sce.service.core.service.container;

import org.springframework.stereotype.Service;
import org.thinking.sce.service.core.domain.container.Totebox;
import org.thinking.sce.service.core.repository.DomainRepository;
import org.thinking.sce.service.core.service.DomainService;

import javax.persistence.EntityManager;

@Service
public class ToteboxService extends DomainService<QTotebox, Totebox, Long> {
    public ToteboxService(EntityManager entityManager, DomainRepository<Totebox, Long> repository) {
        super(entityManager, repository, QTotebox.totebox);
    }
}