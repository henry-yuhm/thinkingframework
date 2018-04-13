package org.thinking.logistics.dispatcher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.thinking.logistics.core.entity.bill.OutboundHeader;
import org.thinking.logistics.dispatcher.service.DispatcherService;

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
    public void arrangeWave(@RequestParam String ownerNumber, @RequestParam String employeeNumber, @RequestBody List<OutboundHeader> headers) throws Exception {
        this.service.arrangeWave(ownerNumber, employeeNumber, headers);
    }

    @PutMapping("/cancelWave")
    public void cancelWave(@RequestParam String ownerNumber, @RequestParam String employeeNumber, @RequestParam String wave) throws Exception {
        this.service.cancelWave(ownerNumber, employeeNumber, wave);
    }

    @PutMapping("/modifyWave")
    public void modifyWave(@RequestParam String ownerNumber, @RequestParam String employeeNumber, @RequestParam String headerNumber) throws Exception {
        this.service.modifyWave(ownerNumber, employeeNumber, headerNumber);
    }

    @PutMapping("/releaseWave")
    public void releaseWave(@RequestParam String ownerNumber, @RequestParam String employeeNumber, @RequestParam String wave) throws Exception {
        this.service.releaseWave(ownerNumber, employeeNumber, wave);
    }

    @PutMapping("/releaseAppointedLocationOrder")
    public void releaseAppointedLocationOrder(@RequestParam String ownerNumber, @RequestParam String employeeNumber, @RequestParam String headerNumber) throws Exception {
        this.service.releaseAppointedLocationOrder(ownerNumber, employeeNumber, headerNumber);
    }

    @PutMapping("/releaseInsertedOrder")
    public void releaseInsertedOrder(@RequestParam String ownerNumber, @RequestParam String employeeNumber, @RequestParam String headerNumber) throws Exception {
        this.service.releaseInsertedOrder(ownerNumber, employeeNumber, headerNumber);
    }

    @PutMapping("/releaseSpecialOrder")
    public void releaseSpecialOrder(@RequestParam String ownerNumber, @RequestParam String employeeNumber, @RequestParam String headerNumber) throws Exception {
        this.service.releaseSpecialOrder(ownerNumber, employeeNumber, headerNumber);
    }

    @PutMapping("/releaseSuspendedOrder")
    public void releaseSuspendedOrder(@RequestParam String ownerNumber, @RequestParam String employeeNumber, @RequestParam String headerNumber) throws Exception {
        this.service.releaseSuspendedOrder(ownerNumber, employeeNumber, headerNumber);
    }
}