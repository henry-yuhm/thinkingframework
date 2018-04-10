package org.thinking.logistics.audit.service;

import com.querydsl.core.types.Predicate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thinking.logistics.audit.entity.AuditType;
import org.thinking.logistics.audit.repository.AuditTypeRepository;

import java.util.List;

@Service
public class AuditTypeService {
    private AuditTypeRepository auditTypeRepository;

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
    public List<AuditType> save(List<AuditType> types) {
        types.forEach(type -> {
            AuditType auditType = this.auditTypeRepository.findByName(type.getName());
            if (auditType != null) {
                auditType.setDescription(type.getDescription());
            }
        });

        return this.auditTypeRepository.saveAll(types);
    }
}