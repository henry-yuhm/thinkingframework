package org.thinking.logistics.workflow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.data.jpa.JpaRepositoryAction;
import org.springframework.web.bind.annotation.*;
import org.thinking.logistics.workflow.service.ActionService;

import java.util.List;

@RestController
@RequestMapping("/action")
public class ActionController {
    private ActionService actionService;

    @Autowired
    public ActionController(ActionService actionService) {
        this.actionService = actionService;
    }

    @GetMapping("/findAll")
    public List<JpaRepositoryAction> findAll() {
        return this.actionService.findAll();
    }

    @PutMapping("/save")
    public boolean save(@RequestBody List<JpaRepositoryAction> actions) {
        return this.actionService.save(actions);
    }
}