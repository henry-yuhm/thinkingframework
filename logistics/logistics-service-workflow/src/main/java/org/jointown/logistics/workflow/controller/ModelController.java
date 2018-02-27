package org.jointown.logistics.workflow.controller;


import org.jointown.logistics.workflow.entity.Workflow;
import org.jointown.logistics.workflow.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/model")
public class ModelController {
    @Autowired
    private ModelService modelService;

    @GetMapping("/findAll")
    public List<Workflow> findAll() {
        return this.modelService.findAll();
    }

    @PutMapping("/save")
    public boolean save(@RequestBody Workflow workflow) {
        return this.modelService.save(workflow);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAll() {
        this.modelService.deleteAll();
    }
}