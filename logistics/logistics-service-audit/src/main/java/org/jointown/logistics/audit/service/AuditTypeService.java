package org.jointown.logistics.audit.service;

import com.querydsl.core.types.Predicate;
import org.jointown.logistics.audit.entity.AuditType;
import org.jointown.logistics.audit.repository.AuditTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuditTypeService {
    @Autowired
    private AuditTypeRepository auditTypeRepository;

    public AuditType findOne(Predicate predicate) {
        return this.auditTypeRepository.findOne(predicate);
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

        return this.auditTypeRepository.save(types);
    }
}