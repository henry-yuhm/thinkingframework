package org.thinking.logistics.transport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.thinking.logistics.transport.entity.TransportBillHeader;

/**
 * Created by Henry on 2017/5/27.
 */
@Repository
public interface TransportBillHeaderRepository extends JpaRepository<TransportBillHeader, Long> {
    TransportBillHeader findByBillHeaderId(long billHeaderId);
}
