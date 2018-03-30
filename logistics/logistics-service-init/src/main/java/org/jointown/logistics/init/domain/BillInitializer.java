package org.jointown.logistics.init.domain;

import org.jointown.logistics.core.entity.bill.OutboundHeader;
import org.jointown.logistics.core.repository.HeaderRepository;

public interface BillInitializer<T extends OutboundHeader> {
    HeaderRepository getRepository();

    T getHeader();

    void verify() throws Exception;

    void initialize() throws Exception;
}