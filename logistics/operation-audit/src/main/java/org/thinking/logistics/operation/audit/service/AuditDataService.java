package org.thinking.logistics.operation.audit.service;

import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thinking.logistics.operation.audit.domain.AuditData;
import org.thinking.logistics.operation.audit.repository.AuditDataRepository;
import org.thinking.logistics.operation.audit.repository.AuditTypeRepository;

import java.util.List;

@Service
public class AuditDataService {
    private final AuditDataRepository auditDataRepository;

    private final AuditTypeRepository auditTypeRepository;

    @Autowired
    public AuditDataService(AuditDataRepository auditDataRepository, AuditTypeRepository auditTypeRepository) {
        this.auditDataRepository = auditDataRepository;
        this.auditTypeRepository = auditTypeRepository;
    }

    public AuditData findOne(Predicate predicate) {
        return this.auditDataRepository.findOne(predicate).get();
    }

    public List<AuditData> findAll(Predicate predicate) {
        return (List<AuditData>) this.auditDataRepository.findAll(predicate);
    }

    @Transactional(rollbackFor = Exception.class)
    public void save(List<AuditData> datas) throws Exception {
//        for (AuditData data : datas) {
//            AuditType auditType = this.auditTypeRepository.findByName(data.getTypes().getName());
//            if (auditType == null) {
//                throw new NullPointerException("审计类型【" + data.getTypes().getName() + "】未定义");
//            } else {
//                data.setTypes(auditType);
//            }
//        }

        this.auditDataRepository.saveAll(datas);
    }
}