package org.jointown.logistics.transport.repository;

import org.jointown.logistics.transport.entity.TransportBillHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Henry on 2017/5/27.
 */
@Repository
public interface TransportBillHeaderRepository extends JpaRepository<TransportBillHeader, Long> {
    TransportBillHeader findByBillHeaderId(long billHeaderId);
}
