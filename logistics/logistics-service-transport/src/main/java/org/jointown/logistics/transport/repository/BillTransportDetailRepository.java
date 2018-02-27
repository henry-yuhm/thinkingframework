package org.jointown.logistics.transport.repository;

import org.jointown.logistics.transport.entity.BillTransportDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Henry on 2017/5/27.
 */
@Repository
public interface BillTransportDetailRepository extends JpaRepository<BillTransportDetailEntity, Long>, JpaSpecificationExecutor<BillTransportDetailEntity> {
    List<BillTransportDetailEntity> findAllByBillHeaderId(long billDetailId);
}
