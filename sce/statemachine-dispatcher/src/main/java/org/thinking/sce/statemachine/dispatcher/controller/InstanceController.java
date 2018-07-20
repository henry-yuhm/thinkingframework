package org.thinking.sce.statemachine.dispatcher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.thinking.sce.statemachine.dispatcher.domain.Workflow;
import org.thinking.sce.statemachine.dispatcher.service.InstanceService;

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