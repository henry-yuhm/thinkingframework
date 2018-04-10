package org.thinking.logistics.workflow.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thinking.logistics.workflow.service.StateKindService;

import java.util.Map;

@RestController
@RequestMapping("/stateKind")
public class StateKindController {
    private StateKindService stateKindService;

    public StateKindController(StateKindService stateKindService) {
        this.stateKindService = stateKindService;
    }

    @GetMapping("/findAll")
    public Map<String, String> findAll() {
        return this.stateKindService.findAll();
    }
}