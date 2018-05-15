package org.thinking.logistics.services.core.service.documents;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thinking.logistics.services.core.domain.documents.InverseDocumentsDetail;
import org.thinking.logistics.services.core.domain.documents.QInverseDocumentsDetail;
import org.thinking.logistics.services.core.repository.DomainRepository;
import org.thinking.logistics.services.core.service.DomainService;

import javax.persistence.EntityManager;

@Service
public class InverseDocumentsService extends DomainService<QInverseDocumentsDetail, InverseDocumentsDetail, Long> {
    @Autowired
    public InverseDocumentsService(EntityManager entityManager, DomainRepository<InverseDocumentsDetail, Long> repository) {
        super(entityManager, repository, QInverseDocumentsDetail.inverseDocumentsDetail);
    }
}