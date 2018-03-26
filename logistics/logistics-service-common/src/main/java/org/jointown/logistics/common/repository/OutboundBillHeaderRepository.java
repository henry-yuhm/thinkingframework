package org.jointown.logistics.common.repository;

import org.jointown.logistics.common.entity.bill.OutboundSaleBillHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutboundBillHeaderRepository extends JpaRepository<OutboundSaleBillHeader, Long> {
}