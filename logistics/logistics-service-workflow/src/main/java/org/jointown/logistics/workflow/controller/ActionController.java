package org.jointown.logistics.workflow.controller;

import org.jointown.logistics.workflow.service.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.data.jpa.JpaRepositoryAction;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/action")
public class ActionController {
    @Autowired
    private ActionService actionService;

    @PutMapping("/save")
    public boolean save(@RequestBody List<JpaRepositoryAction> actions) {
        return this.actionService.save(actions);
    }
}