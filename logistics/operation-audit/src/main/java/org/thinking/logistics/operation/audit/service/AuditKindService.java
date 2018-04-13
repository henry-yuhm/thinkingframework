package org.thinking.logistics.operation.audit.service;

import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thinking.logistics.operation.audit.entity.AuditKind;
import org.thinking.logistics.operation.audit.repository.AuditKindRepository;

import java.util.List;

@Service
public class AuditKindService {
    private final AuditKindRepository auditKindRepository;

    @Autowired
    public AuditKindService(AuditKindRepository auditKindRepository) {
        this.auditKindRepository = auditKindRepository;
    }

    public AuditKind findOne(Predicate predicate) {
        return this.auditKindRepository.findOne(predicate).get();
    }

    public List<AuditKind> findAll(Predicate predicate) {
        return (List<AuditKind>) this.auditKindRepository.findAll(predicate);
    }

    @Transactional(rollbackFor = Exception.class)
    public void save(List<AuditKind> kinds) throws Exception {
        kinds.forEach(type -> {
            AuditKind auditKind = this.auditKindRepository.findByName(type.getName());
            if (auditKind != null) {
                auditKind.setDescription(type.getDescription());
            }
        });

        this.auditKindRepository.saveAll(kinds);
    }
}