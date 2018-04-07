package org.thinking.logistics.workflow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.thinking.logistics.workflow.service.MachineService;

@RestController
@RequestMapping("/stateMachine")
public class MachineController {
    @Autowired
    private MachineService machineService;

    @PutMapping("/start")
    public void start(@RequestParam("machineId") String machineId, @RequestParam("instanceId") String instanceId, @RequestBody(required = false) String parameters) throws Exception {
        this.machineService.start(machineId, String.join(".", machineId, instanceId), parameters);
    }

    @PutMapping("/stop")
    public void stop(@RequestParam("machineId") String machineId, @RequestParam("instanceId") String instanceId) throws Exception {
        this.machineService.stop(machineId, String.join(".", machineId, instanceId));
    }

    @PutMapping("/sendEvent")
    public void sendEvent(@RequestParam("machineId") String machineId, @RequestParam("instanceId") String instanceId, @RequestParam("event") String event) throws Exception {
        this.machineService.sendEvent(machineId, String.join(".", machineId, instanceId), event);
    }
}