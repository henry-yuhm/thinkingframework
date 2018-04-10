package org.thinking.logistics.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.thinking.logistics.core.entity.Owner;
import org.thinking.logistics.core.entity.bill.OutboundHeader;

import java.util.List;

@Repository
public interface OutboundHeaderRepository extends JpaRepository<OutboundHeader, Long> {
    OutboundHeader findByOwnerAndNumber(Owner owner, String number);

    List<OutboundHeader> findAllByWave(String wave);
}