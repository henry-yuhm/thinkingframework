package org.thinking.logistics.services.core.repository.bill;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.entity.bill.OutboundHeader;

import java.util.List;

@Repository
public interface OutboundHeaderRepository extends HeaderRepository<OutboundHeader> {
    List<OutboundHeader> findAllByWave(String wave);
}