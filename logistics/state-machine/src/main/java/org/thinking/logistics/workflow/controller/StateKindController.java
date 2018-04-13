package org.thinking.logistics.workflow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thinking.logistics.workflow.service.StateKindService;

import java.util.Map;

@RestController
@RequestMapping("/stateKind")
public class StateKindController {
    private StateKindService service;

    @Autowired
    public StateKindController(StateKindService service) {
        this.service = service;
    }

    @GetMapping("/findAll")
    public Map<String, String> findAll() {
        return this.service.findAll();
    }
}