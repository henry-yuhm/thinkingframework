package org.thinking.logistics.operation.audit.service;

import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thinking.logistics.operation.audit.entity.AuditData;
import org.thinking.logistics.operation.audit.entity.AuditKind;
import org.thinking.logistics.operation.audit.repository.AuditDataRepository;
import org.thinking.logistics.operation.audit.repository.AuditKindRepository;

import java.util.List;

@Service
public class AuditDataService {
    private final AuditDataRepository auditDataRepository;

    private final AuditKindRepository auditKindRepository;

    @Autowired
    public AuditDataService(AuditDataRepository auditDataRepository, AuditKindRepository auditKindRepository) {
        this.auditDataRepository = auditDataRepository;
        this.auditKindRepository = auditKindRepository;
    }

    public AuditData findOne(Predicate predicate) {
        return this.auditDataRepository.findOne(predicate).get();
    }

    public List<AuditData> findAll(Predicate predicate) {
        return (List<AuditData>) this.auditDataRepository.findAll(predicate);
    }

    @Transactional(rollbackFor = Exception.class)
    public void save(List<AuditData> datas) throws Exception {
        datas.forEach(data -> {
            AuditKind auditKind = this.auditKindRepository.findByName(data.getKind().getName());
            if (auditKind == null) {
                throw new NullPointerException("审计类型【" + data.getKind().getName() + "】未定义");
            } else {
                data.setKind(auditKind);
            }
        });

        this.auditDataRepository.saveAll(datas);
    }
}