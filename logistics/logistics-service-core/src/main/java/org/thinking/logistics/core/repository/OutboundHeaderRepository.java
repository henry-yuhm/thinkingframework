package org.thinking.logistics.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.thinking.logistics.core.entity.bill.OutboundHeader;

@Repository
public interface OutboundHeaderRepository extends JpaRepository<OutboundHeader, Long> {
    OutboundHeader findByWave(String wave);
}