package org.thinking.sce.order.dispatcher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.thinking.sce.order.dispatcher.service.DispatcherService;
import org.thinking.sce.service.core.domain.document.ShipmentOrderHeader;
import org.thinking.sce.service.core.domain.employee.Employee;

import java.util.List;

@RestController
@RequestMapping
public class DispatcherController {
    private final DispatcherService service;

    @Autowired
    public DispatcherController(DispatcherService service) {
        this.service = service;
    }

    @PutMapping("/arrangeWave")
    public void arrangeWave(@RequestParam Employee employee, @RequestBody List<ShipmentOrderHeader> headers) throws Exception {
        this.service.arrangeWave(employee, headers);
    }

    @PutMapping("/cancelWave")
    public void cancelWave(@RequestParam Employee employee, @RequestParam String wave) throws Exception {
        this.service.cancelWave(employee, wave);
    }

    @PutMapping("/modifyWave")
    public void modifyWave(@RequestParam Employee employee, @RequestParam String headerNo) throws Exception {
        this.service.modifyWave(employee, headerNo);
    }

    @PutMapping("/releaseWave")
    public void releaseWave(@RequestParam Employee employee, @RequestParam String wave) throws Exception {
        this.service.releaseWave(employee, wave);
    }

    @PutMapping("/releaseAppointedLocationOrder")
    public void releaseAppointedLocationOrder(@RequestParam Employee employee, @RequestParam String headerNo) throws Exception {
        this.service.releaseAppointedLocationOrder(employee, headerNo);
    }

    @PutMapping("/releaseInsertedOrder")
    public void releaseInsertedOrder(@RequestParam Employee employee, @RequestParam String headerNo) throws Exception {
        this.service.releaseInsertedOrder(employee, headerNo);
    }

    @PutMapping("/releaseSpecialOrder")
    public void releaseSpecialOrder(@RequestParam Employee employee, @RequestParam String headerNo) throws Exception {
        this.service.releaseSpecialOrder(employee, headerNo);
    }

    @PutMapping("/releaseSuspendedOrder")
    public void releaseSuspendedOrder(@RequestParam Employee employee, @RequestParam String headerNo) throws Exception {
        this.service.releaseSuspendedOrder(employee, headerNo);
    }
}