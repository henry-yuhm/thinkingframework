package org.jointown.logistics.workflow.controller;

import org.jointown.logistics.workflow.service.StateKindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/stateKind")
public class StateKindController {
    @Autowired
    private StateKindService stateKindService;

    @GetMapping("/findAll")
    public Map<String, String> findAll() {
        return this.stateKindService.findAll();
    }
}