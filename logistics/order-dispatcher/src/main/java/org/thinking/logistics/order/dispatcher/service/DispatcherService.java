package org.thinking.logistics.order.dispatcher.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thinking.logistics.order.dispatcher.domain.*;
import org.thinking.logistics.services.core.domain.BillAdapter;
import org.thinking.logistics.services.core.entity.Owner;
import org.thinking.logistics.services.core.entity.bill.OutboundHeader;

import java.util.List;

@Service
public class DispatcherService extends BillAdapter {
    @Transactional(rollbackFor = Exception.class)
    public void arrangeWave(String ownerNumber, String employeeNumber, List<OutboundHeader> headers) throws Exception {
        new OutboundOrderDispatcher(this.getEmployee(this.getOwner(ownerNumber), employeeNumber), headers).arrangeWave();
    }

    @Transactional(rollbackFor = Exception.class)
    public void cancelWave(String ownerNumber, String employeeNumber, String wave) throws Exception {
        new OutboundOrderDispatcher(this.getEmployee(this.getOwner(ownerNumber), employeeNumber), this.getOutboundHeaders(wave)).cancelWave();
    }

    @Transactional(rollbackFor = Exception.class)
    public void modifyWave(String ownerNumber, String employeeNumber, String headerNumber) throws Exception {
        Owner owner = this.getOwner(ownerNumber);
        new OutboundOrderDispatcher(this.getEmployee(owner, employeeNumber), this.getOutboundHeader(owner, headerNumber)).modifyWave();
    }

    @Transactional(rollbackFor = Exception.class)
    public void releaseWave(String ownerNumber, String employeeNumber, String wave) throws Exception {
        new OutboundOrderDispatcher(this.getEmployee(this.getOwner(ownerNumber), employeeNumber), this.getOutboundHeaders(wave)).releaseWave();
    }

    @Transactional(rollbackFor = Exception.class)
    public void releaseAppointedLocationOrder(String ownerNumber, String employeeNumber, String headerNumber) throws Exception {
        Owner owner = this.getOwner(ownerNumber);
        new AppointedLocationDispatcher(this.getEmployee(owner, employeeNumber), this.getOutboundHeader(owner, headerNumber)).releaseOrder();
    }

    @Transactional(rollbackFor = Exception.class)
    public void releaseInsertedOrder(String ownerNumber, String employeeNumber, String headerNumber) throws Exception {
        Owner owner = this.getOwner(ownerNumber);
        new InsertedOrderDispatcher(this.getEmployee(owner, employeeNumber), this.getOutboundHeader(owner, headerNumber)).releaseOrder();
    }

    @Transactional(rollbackFor = Exception.class)
    public void releaseSpecialOrder(String ownerNumber, String employeeNumber, String headerNumber) throws Exception {
        Owner owner = this.getOwner(ownerNumber);
        new SpecialOrderDispatcher(this.getEmployee(owner, employeeNumber), this.getOutboundHeader(owner, headerNumber)).releaseOrder();
    }

    @Transactional(rollbackFor = Exception.class)
    public void releaseSuspendedOrder(String ownerNumber, String employeeNumber, String headerNumber) throws Exception {
        Owner owner = this.getOwner(ownerNumber);
        new SuspendedOrderDispatcher(this.getEmployee(owner, employeeNumber), this.getOutboundHeader(owner, headerNumber)).releaseOrder();
    }
}