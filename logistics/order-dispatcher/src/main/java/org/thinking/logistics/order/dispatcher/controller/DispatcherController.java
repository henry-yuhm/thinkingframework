package org.thinking.logistics.order.dispatcher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.thinking.logistics.order.dispatcher.service.DispatcherService;
import org.thinking.logistics.services.core.entity.Employee;
import org.thinking.logistics.services.core.entity.bill.OutboundHeader;

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
    public void arrangeWave(@RequestParam Employee employee, @RequestBody List<OutboundHeader> headers) throws Exception {
        this.service.arrangeWave(employee, headers);
    }

    @PutMapping("/cancelWave")
    public void cancelWave(@RequestParam Employee employee, @RequestParam String wave) throws Exception {
        this.service.cancelWave(employee, wave);
    }

    @PutMapping("/modifyWave")
    public void modifyWave(@RequestParam Employee employee, @RequestParam String headerNumber) throws Exception {
        this.service.modifyWave(employee, headerNumber);
    }

    @PutMapping("/releaseWave")
    public void releaseWave(@RequestParam Employee employee, @RequestParam String wave) throws Exception {
        this.service.releaseWave(employee, wave);
    }

    @PutMapping("/releaseAppointedLocationOrder")
    public void releaseAppointedLocationOrder(@RequestParam Employee employee, @RequestParam String headerNumber) throws Exception {
        this.service.releaseAppointedLocationOrder(employee, headerNumber);
    }

    @PutMapping("/releaseInsertedOrder")
    public void releaseInsertedOrder(@RequestParam Employee employee, @RequestParam String headerNumber) throws Exception {
        this.service.releaseInsertedOrder(employee, headerNumber);
    }

    @PutMapping("/releaseSpecialOrder")
    public void releaseSpecialOrder(@RequestParam Employee employee, @RequestParam String headerNumber) throws Exception {
        this.service.releaseSpecialOrder(employee, headerNumber);
    }

    @PutMapping("/releaseSuspendedOrder")
    public void releaseSuspendedOrder(@RequestParam Employee employee, @RequestParam String headerNumber) throws Exception {
        this.service.releaseSuspendedOrder(employee, headerNumber);
    }
}