package org.thinking.sce.statemachine.dispatcher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.data.jpa.JpaRepositoryGuard;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thinking.sce.statemachine.dispatcher.service.GuardService;

import java.util.List;

@RestController
@RequestMapping("/guard")
public class GuardController {
    private GuardService service;

    @Autowired
    public GuardController(GuardService service) {
        this.service = service;
    }

    @GetMapping("/findAll")
    public List<JpaRepositoryGuard> findAll() {
        return this.service.findAll();
    }

    @PutMapping("/save")
    public void save(@RequestBody List<JpaRepositoryGuard> guards) throws Exception {
        this.service.save(guards);
    }
}