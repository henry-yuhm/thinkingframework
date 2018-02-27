package org.jointown.logistics.workflow.controller;

import org.jointown.logistics.workflow.service.GuardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.data.jpa.JpaRepositoryGuard;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/guard")
public class GuardController {
    @Autowired
    private GuardService guardService;

    @PutMapping("/save")
    public boolean save(@RequestBody List<JpaRepositoryGuard> guards) {
        return this.guardService.save(guards);
    }
}