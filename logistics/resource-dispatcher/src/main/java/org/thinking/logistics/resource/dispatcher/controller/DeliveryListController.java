package org.thinking.logistics.resource.dispatcher.controller;

import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.*;
import org.thinking.logistics.resource.dispatcher.entity.DeliveryList;
import org.thinking.logistics.resource.dispatcher.service.DeliveryListService;

import java.util.List;

/**
 * Created by Henry on 2017/6/14.
 */
@RestController
@RequestMapping("/deliveryList")
public class DeliveryListController {
    private DeliveryListService service;

    @Autowired
    public DeliveryListController(DeliveryListService service) {
        this.service = service;
    }

    @GetMapping("/findOne")
    public DeliveryList findOne(@QuerydslPredicate(root = DeliveryList.class) Predicate predicate) {
        return this.service.findOne(predicate);
    }

    @GetMapping("/findAll")
    public List<DeliveryList> findAll(@QuerydslPredicate(root = DeliveryList.class) Predicate predicate) {
        return this.service.findAll(predicate);
    }

    @PostMapping("/save")
    public void save(@RequestBody String data) {
        this.service.save(data);
    }

    @GetMapping("/acquireFileName")
    public String acquireFileName(@QuerydslPredicate(root = DeliveryList.class) Predicate predicate) {
        return this.service.acquireFileName(predicate);
    }
}