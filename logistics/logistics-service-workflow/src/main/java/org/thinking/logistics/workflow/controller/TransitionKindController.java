package org.thinking.logistics.workflow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thinking.logistics.workflow.service.TransitionKindService;

import java.util.Map;

@RestController
@RequestMapping("/transitionKind")
public class TransitionKindController {
    private TransitionKindService transitionKindService;

    @Autowired
    public TransitionKindController(TransitionKindService transitionKindService) {
        this.transitionKindService = transitionKindService;
    }

    @GetMapping("/findAll")
    public Map<String, String> findAll() {
        return this.transitionKindService.findAll();
    }
}