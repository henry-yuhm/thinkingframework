package org.jointown.logistics.init.domain;

import org.jointown.logistics.core.entity.bill.OutboundBillHeader;
import org.jointown.logistics.core.repository.BillHeaderRepository;

public interface BillInitializer<T extends OutboundBillHeader> {
    BillHeaderRepository getRepository();

    T getHeader();

    void verify() throws Exception;

    void initialize() throws Exception;
}