package org.thinking.sce.order.dispatcher.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thinking.sce.order.dispatcher.domain.AppointedLocationDispatcher;
import org.thinking.sce.order.dispatcher.domain.InsertedOrderDispatcher;
import org.thinking.sce.order.dispatcher.domain.OutboundOrderDispatcher;
import org.thinking.sce.order.dispatcher.domain.SpecialOrderDispatcher;
import org.thinking.sce.order.dispatcher.domain.SuspendedOrderDispatcher;
import org.thinking.sce.service.core.domain.common.Warehouse;
import org.thinking.sce.service.core.domain.document.ShipmentOrderHeader;
import org.thinking.sce.service.core.domain.employee.Employee;
import org.thinking.sce.service.core.service.document.ShipmentOrderService;

import java.util.List;

@Service
public class DispatcherService {
    private ShipmentOrderService orderService;

    @Autowired
    public DispatcherService(ShipmentOrderService orderService) {
        this.orderService = orderService;
    }

    @Transactional(rollbackFor = Exception.class)
    public void arrangeWave(Employee employee, List<ShipmentOrderHeader> headers) throws Exception {
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