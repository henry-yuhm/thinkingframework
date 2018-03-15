package org.jointown.logistics.transport.repository;

import org.jointown.logistics.transport.entity.TransportBillDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Henry on 2017/5/27.
 */
@Repository
public interface TransportBillDetailRepository extends JpaRepository<TransportBillDetail, Long>, JpaSpecificationExecutor<TransportBillDetail> {
    List<TransportBillDetail> findAllByBillHeaderId(long billDetailId);
}
