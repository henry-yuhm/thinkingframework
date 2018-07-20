package org.thinking.sce.statemachine.dispatcher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.data.jpa.JpaRepositoryAction;
import org.springframework.web.bind.annotation.*;
import org.thinking.sce.statemachine.dispatcher.service.ActionService;

import java.util.List;

@RestController
@RequestMapping("/action")
public class ActionController {
    private ActionService service;

    @Autowired
    public ActionController(ActionService service) {
        this.service = service;
    }

    @GetMapping("/findAll")
    public List<JpaRepositoryAction> findAll() {
        return this.service.findAll();
    }

    @PutMapping("/save")
    public void save(@RequestBody List<JpaRepositoryAction> actions) throws Exception {
        this.service.save(actions);
    }
}