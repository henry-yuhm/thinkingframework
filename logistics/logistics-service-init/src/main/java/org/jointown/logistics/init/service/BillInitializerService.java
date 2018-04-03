package org.jointown.logistics.init.service;

import org.jointown.logistics.core.entity.bill.OutboundHeader;
import org.jointown.logistics.core.repository.HeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillInitializerService {
    @Autowired
    private HeaderRepository<OutboundHeader> repository;

    public void initialize(long id) {

    }
}