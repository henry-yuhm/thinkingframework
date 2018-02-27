package org.jointown.logistics.transport.service;

import org.jointown.logistics.transport.entity.BillTransportDetailEntity;
import org.jointown.logistics.transport.entity.BillTransportHeaderEntity;
import org.jointown.logistics.transport.repository.BillTransportDetailRepository;
import org.jointown.logistics.transport.repository.BillTransportHeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Henry on 2017/5/27.
 */
@Service
public class BillTransportService {
    @Autowired
    private BillTransportHeaderRepository billTransportHeaderRepository;

    @Autowired
    private BillTransportDetailRepository billTransportDetailRepository;

    public BillTransportHeaderEntity findBillTransportHeader(long billHeaderId) {
        return billTransportHeaderRepository.findByBillHeaderId(billHeaderId);
    }

    public List<BillTransportDetailEntity> findBillTransportDetail(long billHeaderId) {
        return billTransportDetailRepository.findAllByBillHeaderId(billHeaderId);
    }
}
