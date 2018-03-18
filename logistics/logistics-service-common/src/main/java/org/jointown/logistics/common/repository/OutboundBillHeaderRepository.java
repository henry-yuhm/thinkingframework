package org.jointown.logistics.common.repository;

import org.jointown.logistics.common.entity.OutboundBillHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutboundBillHeaderRepository extends JpaRepository<OutboundBillHeader, Long> {
}