package org.thinking.logistics.dispatcher.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thinking.logistics.core.domain.BusinessAdapter;
import org.thinking.logistics.core.entity.bill.OutboundHeader;
import org.thinking.logistics.core.repository.OutboundHeaderRepository;
import org.thinking.logistics.core.repository.OwnerRepository;
import org.thinking.logistics.dispatcher.domain.OutboundOrderDispatcher;

import java.util.List;

@Service
public class DispatcherService extends BusinessAdapter {
    private OwnerRepository ownerRepository;

    private OutboundHeaderRepository headerRepository;

    @Autowired
    public DispatcherService(OwnerRepository ownerRepository, OutboundHeaderRepository headerRepository) {
        this.ownerRepository = ownerRepository;
        this.headerRepository = headerRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    public void arrangeWave(String ownerNumber, String employeeNumber, List<OutboundHeader> headers) throws Exception {
        new OutboundOrderDispatcher(this.getEmployee(this.ownerRepository.findByNumber(ownerNumber), employeeNumber), headers).arrangeWave();
    }

    @Transactional(rollbackFor = Exception.class)
    public void cancelWave(String ownerNumber, String employeeNumber, String wave) throws Exception {
        new OutboundOrderDispatcher(this.getEmployee(this.ownerRepository.findByNumber(ownerNumber), employeeNumber), this.headerRepository.findAllByWave(wave)).cancelWave();
    }

    @Transactional(rollbackFor = Exception.class)
    public void modifyWave(String ownerNumber, String employeeNumber, String headerNumber) throws Exception {
        new OutboundOrderDispatcher(this.getEmployee(this.ownerRepository.findByNumber(ownerNumber), employeeNumber), this.headerRepository.findByOwnerAndNumber(this.ownerRepository.findByNumber(ownerNumber), headerNumber)).modifyWave();
    }
}