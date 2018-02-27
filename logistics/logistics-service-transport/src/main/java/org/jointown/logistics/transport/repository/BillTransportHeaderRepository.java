package org.jointown.logistics.transport.repository;

import org.jointown.logistics.transport.entity.BillTransportHeaderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by Henry on 2017/5/27.
 */
@Repository
public interface BillTransportHeaderRepository extends JpaRepository<BillTransportHeaderEntity, Long>, JpaSpecificationExecutor<BillTransportHeaderEntity> {
    BillTransportHeaderEntity findByBillHeaderId(long billHeaderId);
}
