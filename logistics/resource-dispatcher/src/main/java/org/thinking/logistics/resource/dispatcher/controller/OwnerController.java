package org.thinking.logistics.resource.dispatcher.controller;

import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.*;
import org.thinking.logistics.services.core.domain.Owner;
import org.thinking.logistics.services.core.service.OwnerService;

import java.util.List;

@RestController
@RequestMapping("/owner")
public class OwnerController {
    private OwnerService service;

    @Autowired
    public OwnerController(OwnerService service) {
        this.service = service;
    }

    @GetMapping("/findOne")
    public Owner findOne(@QuerydslPredicate(root = Owner.class) Predicate predicate) {
        return this.service.getRepository().findOne(predicate).get();
    }

    @GetMapping("/findAll")
    public List<Owner> findAll(@QuerydslPredicate(root = Owner.class) Predicate predicate) {
        return (List<Owner>) this.service.getRepository().findAll(predicate);
    }

    @PutMapping("/save")
    public Owner save(@RequestBody Owner owner) throws Exception {
        return this.service.save(owner);
    }

    @PutMapping("/saveAll")
    public List<Owner> saveAll(@RequestBody List<Owner> owners) throws Exception {
        return this.service.saveAll(owners);
    }
}