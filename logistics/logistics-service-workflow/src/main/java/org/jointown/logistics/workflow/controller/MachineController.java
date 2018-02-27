package org.jointown.logistics.workflow.controller;

import org.jointown.logistics.workflow.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stateMachine")
public class MachineController {
    @Autowired
    private MachineService machineService;

    @PutMapping("/start")
    public void start(@RequestParam("machineId") String machineId, @RequestParam("instanceId") String instanceId, @RequestBody(required = false) String parameters) throws Exception {
        this.machineService.start(machineId, instanceId, parameters);
    }

    @PutMapping("/stop")
    public void stop(@RequestParam("machineId") String machineId, @RequestParam("instanceId") String instanceId) throws Exception {
        this.machineService.stop(machineId, instanceId);
    }

    @PutMapping("/sendEvent")
    public void sendEvent(@RequestParam("machineId") String machineId, @RequestParam("instanceId") String instanceId, @RequestParam("event") String event) throws Exception {
        this.machineService.sendEvent(machineId, instanceId, event);
    }
}