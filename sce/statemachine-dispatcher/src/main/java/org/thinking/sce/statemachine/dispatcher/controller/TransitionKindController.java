package org.thinking.sce.statemachine.dispatcher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thinking.sce.statemachine.dispatcher.service.TransitionKindService;

import java.util.Map;

@RestController
@RequestMapping("/transitionKind")
public class TransitionKindController {
    private TransitionKindService service;

    @Autowired
    public TransitionKindController(TransitionKindService service) {
        this.service = service;
    }

    @GetMapping("/findAll")
    public Map<String, String> findAll() {
        return this.service.findAll();
    }
}