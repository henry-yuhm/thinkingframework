package org.thinking.logistics.services.core.service.documents;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thinking.logistics.services.core.domain.documents.InverseOrderDetail;
import org.thinking.logistics.services.core.domain.documents.QInverseOrderDetail;
import org.thinking.logistics.services.core.repository.DomainRepository;
import org.thinking.logistics.services.core.service.DomainService;

import javax.persistence.EntityManager;

@Service
public class InverseOrderService extends DomainService<QInverseOrderDetail, InverseOrderDetail, Long> {
    @Autowired
    public InverseOrderService(EntityManager entityManager, DomainRepository<InverseOrderDetail, Long> repository) {
        super(entityManager, repository, QInverseOrderDetail.inverseOrderDetail);
    }
}