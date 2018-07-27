package org.thinking.sce.statemachine.dispatcher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thinking.sce.statemachine.dispatcher.service.MachineService;

@RestController
@RequestMapping("/stateMachine")
public class MachineController {
    private MachineService service;

    @Autowired
    public MachineController(MachineService service) {
        this.service = service;
    }

    @PutMapping("/start")
    public void start(@RequestParam("machineId") String machineId, @RequestParam("instanceId") String instanceId, @RequestBody(required = false) String parameters) throws Exception {
        this.service.start(machineId, String.join(".", machineId, instanceId), parameters);
    }

    @PutMapping("/stop")
    public void stop(@RequestParam("machineId") String machineId, @RequestParam("instanceId") String instanceId) throws Exception {
        this.service.stop(machineId, String.join(".", machineId, instanceId));
    }

    @PutMapping("/sendEvent")
    public void sendEvent(@RequestParam("machineId") String machineId, @RequestParam("instanceId") String instanceId, @RequestParam("event") String event) throws Exception {
        this.service.sendEvent(machineId, String.join(".", machineId, instanceId), event);
    }
}