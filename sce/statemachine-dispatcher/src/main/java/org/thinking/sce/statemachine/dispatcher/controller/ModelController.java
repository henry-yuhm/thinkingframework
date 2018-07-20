package org.thinking.sce.statemachine.dispatcher.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.thinking.sce.statemachine.dispatcher.domain.Workflow;
import org.thinking.sce.statemachine.dispatcher.service.ModelService;

import java.util.List;

@RestController
@RequestMapping("/model")
public class ModelController {
    private ModelService service;

    @Autowired
    public ModelController(ModelService service) {
        this.service = service;
    }

    @GetMapping("/findOne")
    public Workflow findOne(String id) {
        return this.service.findOne(id);
    }

    @GetMapping("/findAll")
    public List<Workflow> findAll() {
        return this.service.findAll();
    }

    @PutMapping("/save")
    public void save(@RequestBody Workflow workflow) throws Exception {
        this.service.save(workflow);
    }
}