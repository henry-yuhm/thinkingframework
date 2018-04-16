package org.thinking.logistics.order.dispatcher.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thinking.logistics.order.dispatcher.domain.*;
import org.thinking.logistics.services.core.entity.Employee;
import org.thinking.logistics.services.core.entity.bill.OutboundHeader;
import org.thinking.logistics.services.core.repository.bill.OutboundHeaderRepository;

import java.util.List;

@Service
public class DispatcherService {
    private OutboundHeaderRepository headerRepository;

    @Autowired
    public DispatcherService(OutboundHeaderRepository headerRepository) {
        this.headerRepository = headerRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    public void arrangeWave(Employee employee, List<OutboundHeader> headers) throws Exception {
        new OutboundOrderDispatcher(employee, headers).arrangeWave();
    }

    @Transactional(rollbackFor = Exception.class)
    public void cancelWave(Employee employee, String wave) throws Exception {
        new OutboundOrderDispatcher(employee, this.headerRepository.findAllByWave(wave)).cancelWave();
    }

    @Transactional(rollbackFor = Exception.class)
    public void modifyWave(Employee employee, String headerNumber) throws Exception {
        new OutboundOrderDispatcher(employee, this.headerRepository.findByOwnerAndNumber(employee.getOwner(), headerNumber)).modifyWave();
    }

    @Transactional(rollbackFor = Exception.class)
    public void releaseWave(Employee employee, String wave) throws Exception {
        new OutboundOrderDispatcher(employee, this.headerRepository.findAllByWave(wave)).releaseWave();
    }

    @Transactional(rollbackFor = Exception.class)
    public void releaseAppointedLocationOrder(Employee employee, String headerNumber) throws Exception {
        new AppointedLocationDispatcher(employee, this.headerRepository.findByOwnerAndNumber(employee.getOwner(), headerNumber)).releaseOrder();
    }

    @Transactional(rollbackFor = Exception.class)
    public void releaseInsertedOrder(Employee employee, String headerNumber) throws Exception {
        new InsertedOrderDispatcher(employee, this.headerRepository.findByOwnerAndNumber(employee.getOwner(), headerNumber)).releaseOrder();
    }

    @Transactional(rollbackFor = Exception.class)
    public void releaseSpecialOrder(Employee employee, String headerNumber) throws Exception {
        new SpecialOrderDispatcher(employee, this.headerRepository.findByOwnerAndNumber(employee.getOwner(), headerNumber)).releaseOrder();
    }

    @Transactional(rollbackFor = Exception.class)
    public void releaseSuspendedOrder(Employee employee, String headerNumber) throws Exception {
        new SuspendedOrderDispatcher(employee, this.headerRepository.findByOwnerAndNumber(employee.getOwner(), headerNumber)).releaseOrder();
    }
}