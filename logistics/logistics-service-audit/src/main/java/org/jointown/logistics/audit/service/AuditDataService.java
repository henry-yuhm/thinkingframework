package org.jointown.logistics.audit.service;

import com.querydsl.core.types.Predicate;
import org.jointown.logistics.audit.entity.AuditData;
import org.jointown.logistics.audit.entity.AuditType;
import org.jointown.logistics.audit.repository.AuditDataRepository;
import org.jointown.logistics.audit.repository.AuditTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuditDataService {
    @Autowired
    private AuditDataRepository auditDataRepository;

    @Autowired
    private AuditTypeRepository auditTypeRepository;

    public AuditData findOne(Predicate predicate) {
        return this.auditDataRepository.findOne(predicate);
    }

    public List<AuditData> findAll(Predicate predicate) {
        return (List<AuditData>) this.auditDataRepository.findAll(predicate);
    }

    @Transactional(rollbackFor = Exception.class)
    public List<AuditData> save(List<AuditData> datas) {
        datas.forEach(data -> {
            AuditType auditType = this.auditTypeRepository.findByName(data.getAuditType().getName());
            if (auditType == null) {
                throw new NullPointerException("审计类型【" + data.getAuditType().getName() + "】未定义");
            } else {
                data.setAuditType(auditType);
            }
        });

        return this.auditDataRepository.save(datas);
    }
}