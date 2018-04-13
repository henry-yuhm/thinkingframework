package org.thinking.logistics.statemachine.dispatcher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thinking.logistics.statemachine.dispatcher.entity.Monitor;
import org.thinking.logistics.statemachine.dispatcher.service.MonitorService;

import java.util.List;

@RestController
@RequestMapping("/monitor")
public class MonitorController {
    private MonitorService service;

    @Autowired
    public MonitorController(MonitorService service) {
        this.service = service;
    }

    @GetMapping("/findAll")
    public List<Monitor> findAll() {
        return this.service.findAll();
    }

    @GetMapping("/getAll")
    public List<String> getAll() {
        return this.service.getAll();
    }
}