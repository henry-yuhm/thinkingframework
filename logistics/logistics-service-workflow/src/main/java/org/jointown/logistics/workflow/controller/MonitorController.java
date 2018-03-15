package org.jointown.logistics.workflow.controller;

import org.jointown.logistics.workflow.entity.Monitor;
import org.jointown.logistics.workflow.service.MonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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