package org.thinking.sce.statemachine.dispatcher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.thinking.sce.statemachine.dispatcher.domain.Monitor;
import org.thinking.sce.statemachine.dispatcher.service.MonitorService;

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