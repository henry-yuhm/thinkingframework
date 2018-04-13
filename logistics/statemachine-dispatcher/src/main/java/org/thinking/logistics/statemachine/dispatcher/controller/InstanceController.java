package org.thinking.logistics.statemachine.dispatcher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thinking.logistics.statemachine.dispatcher.entity.Workflow;
import org.thinking.logistics.statemachine.dispatcher.service.InstanceService;

@RestController
@RequestMapping("/instance")
public class InstanceController {
    private InstanceService service;

    @Autowired
    public InstanceController(InstanceService service) {
        this.service = service;
    }

    @GetMapping("/findOne")
    public Workflow findOne(String machineId, String instanceId) {
        return this.service.findOne(machineId, instanceId);
    }
}