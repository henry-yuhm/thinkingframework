package org.thinking.logistics.workflow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thinking.logistics.workflow.entity.Workflow;
import org.thinking.logistics.workflow.service.InstanceService;

@RestController
@RequestMapping("/instance")
public class InstanceController {
    private InstanceService instanceService;

    @Autowired
    public InstanceController(InstanceService instanceService) {
        this.instanceService = instanceService;
    }

    @GetMapping("/findOne")
    public Workflow findOne(String machineId, String instanceId) {
        return this.instanceService.findOne(machineId, instanceId);
    }
}