package org.thinking.logistics.services.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thinking.logistics.services.core.domain.container.Container;
import org.thinking.logistics.services.core.domain.container.QContainer;
import org.thinking.logistics.services.core.repository.DomainRepository;

import javax.persistence.EntityManager;

@Service
public class ContainerService extends DomainService<QContainer, Container, Long> {
    @Autowired
    public ContainerService(EntityManager entityManager, DomainRepository<Container, Long> repository) {
        super(entityManager, repository, QContainer.container);
    }
}