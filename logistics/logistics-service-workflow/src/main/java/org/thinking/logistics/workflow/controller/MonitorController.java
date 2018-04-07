package org.thinking.logistics.workflow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thinking.logistics.workflow.entity.Monitor;
import org.thinking.logistics.workflow.service.MonitorService;

import java.util.List;

@RestController
@RequestMapping("/monitor")
public class MonitorController {
    @Autowired
    private MonitorService monitorService;

    @GetMapping("/findAll")
    public List<Monitor> findAll() {
        return this.monitorService.findAll();
    }
}