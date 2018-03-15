package org.jointown.logistics.transport.repository;

import org.jointown.logistics.transport.entity.TransportBillHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by Henry on 2017/5/27.
 */
@Repository
public interface TransportBillHeaderRepository extends JpaRepository<TransportBillHeader, Long>, JpaSpecificationExecutor<TransportBillHeader> {
    TransportBillHeader findByBillHeaderId(long billHeaderId);
}
