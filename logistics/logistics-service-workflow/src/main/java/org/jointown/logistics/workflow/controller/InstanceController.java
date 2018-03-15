package org.jointown.logistics.workflow.controller;

import org.jointown.logistics.workflow.entity.Workflow;
import org.jointown.logistics.workflow.service.InstanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/instance")
public class InstanceController {
    @Autowired
    private InstanceService instanceService;

    @GetMapping("/findOne")
    public Workflow findOne(String machineId, String instanceId) {
        return this.instanceService.findOne(machineId, instanceId);
    }
}