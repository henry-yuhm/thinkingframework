package org.jointown.logistics.workflow.controller;

import org.jointown.logistics.workflow.service.GuardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.data.jpa.JpaRepositoryGuard;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guard")
public class GuardController {
    @Autowired
    private GuardService guardService;

    @GetMapping("/findAll")
    public List<JpaRepositoryGuard> findAll() {
        return this.guardService.findAll();
    }

    @PutMapping("/save")
    public boolean save(@RequestBody List<JpaRepositoryGuard> guards) {
        return this.guardService.save(guards);
    }
}