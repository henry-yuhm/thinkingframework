package org.thinking.logistics.transport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.thinking.logistics.transport.entity.TransportBillDetail;

import java.util.List;

/**
 * Created by Henry on 2017/5/27.
 */
@Repository
public interface TransportBillDetailRepository extends JpaRepository<TransportBillDetail, Long> {
    List<TransportBillDetail> findAllByBillHeaderId(long billDetailId);
}
