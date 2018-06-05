package org.thinking.logistics.order.dispatcher.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thinking.logistics.order.dispatcher.domain.*;
import org.thinking.logistics.services.core.domain.core.Warehouse;
import org.thinking.logistics.services.core.domain.documents.OutboundOrderHeader;
import org.thinking.logistics.services.core.domain.employee.Employee;
import org.thinking.logistics.services.core.service.documents.OutboundOrderService;

import java.util.List;

@Service
public class DispatcherService {
    private OutboundOrderService orderService;

    @Autowired
    public DispatcherService(OutboundOrderService orderService) {
        this.orderService = orderService;
    }

    @Transactional(rollbackFor = Exception.class)
    public void arrangeWave(Employee employee, List<OutboundOrderHeader> headers) throws Exception {
        new OutboundOrderDispatcher(employee, headers).arrangeWave();
    }

    @Transactional(rollbackFor = Exception.class)
    public void cancelWave(Employee employee, String wave) throws Exception {
        new OutboundOrderDispatcher(employee, this.orderService.acquire(new Warehouse(), wave, true)).cancelWave();
    }

    @Transactional(rollbackFor = Exception.class)
    public void modifyWave(Employee employee, String headerNo) throws Exception {
        new OutboundOrderDispatcher(employee, this.orderService.acquire(employee.getOwner(), headerNo, true)).modifyWave();
    }

    @Transactional(rollbackFor = Exception.class)
    public void releaseWave(Employee employee, String wave) throws Exception {
        new OutboundOrderDispatcher(employee, this.orderService.acquire(new Warehouse(), wave, true)).releaseWave();
    }

    @Transactional(rollbackFor = Exception.class)
    public void releaseAppointedLocationOrder(Employee employee, String headerNo) throws Exception {
        new AppointedLocationDispatcher(employee, this.orderService.acquire(employee.getOwner(), headerNo, true)).releaseOrder();
    }

    @Transactional(rollbackFor = Exception.class)
    public void releaseInsertedOrder(Employee employee, String headerNo) throws Exception {
        new InsertedOrderDispatcher(employee, this.orderService.acquire(employee.getOwner(), headerNo, true)).releaseOrder();
    }

    @Transactional(rollbackFor = Exception.class)
    public void releaseSpecialOrder(Employee employee, String headerNo) throws Exception {
        new SpecialOrderDispatcher(employee, this.orderService.acquire(employee.getOwner(), headerNo, true)).releaseOrder();
    }

    @Transactional(rollbackFor = Exception.class)
    public void releaseSuspendedOrder(Employee employee, String headerNo) throws Exception {
        new SuspendedOrderDispatcher(employee, this.orderService.acquire(employee.getOwner(), headerNo, true)).releaseOrder();
    }
}