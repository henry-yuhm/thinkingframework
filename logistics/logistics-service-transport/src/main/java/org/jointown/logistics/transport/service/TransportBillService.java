package org.jointown.logistics.transport.service;

import org.jointown.logistics.transport.entity.TransportBillDetail;
import org.jointown.logistics.transport.entity.TransportBillHeader;
import org.jointown.logistics.transport.repository.TransportBillDetailRepository;
import org.jointown.logistics.transport.repository.TransportBillHeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
