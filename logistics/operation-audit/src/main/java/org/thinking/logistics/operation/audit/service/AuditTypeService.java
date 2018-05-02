package org.thinking.logistics.operation.audit.service;

import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thinking.logistics.operation.audit.domain.AuditType;
import org.thinking.logistics.operation.audit.repository.AuditTypeRepository;

import java.util.List;

@Service
public class AuditTypeService {
    private final AuditTypeRepository auditTypeRepository;

    @Autowired
    public AuditTypeService(AuditTypeRepository auditTypeRepository) {
        this.auditTypeRepository = auditTypeRepository;
    }

    public AuditType findOne(Predicate predicate) {
        return this.auditTypeRepository.findOne(predicate).get();
    }

    public List<AuditType> findAll(Predicate predicate) {
        return (List<AuditType>) this.auditTypeRepository.findAll(predicate);
    }

    @Transactional(rollbackFor = Exception.class)
    public void save(List<AuditType> types) throws Exception {
        types.forEach(type -> {
            AuditType auditType = this.auditTypeRepository.findByName(type.getName());
            if (auditType != null) {
                auditType.setDescription(type.getDescription());
            }
        });

        this.auditTypeRepository.saveAll(types);
    }
}