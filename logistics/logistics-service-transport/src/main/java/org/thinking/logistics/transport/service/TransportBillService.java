package org.thinking.logistics.transport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thinking.logistics.transport.entity.TransportBillDetail;
import org.thinking.logistics.transport.entity.TransportBillHeader;
import org.thinking.logistics.transport.repository.TransportBillDetailRepository;
import org.thinking.logistics.transport.repository.TransportBillHeaderRepository;

import java.util.List;

/**
 * Created by Henry on 2017/5/27.
 */
@Service
public class TransportBillService {
    @Autowired
    private TransportBillHeaderRepository transportBillHeaderRepository;

    @Autowired
    private TransportBillDetailRepository transportBillDetailRepository;

    public TransportBillHeader findBillTransportHeader(long billHeaderId) {
        return transportBillHeaderRepository.findByBillHeaderId(billHeaderId);
    }

    public List<TransportBillDetail> findBillTransportDetail(long billHeaderId) {
        return transportBillDetailRepository.findAllByBillHeaderId(billHeaderId);
    }
}
