package org.thinking.logistics.resource.controller;

import com.querydsl.core.types.Predicate;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.*;
import org.thinking.logistics.resource.entity.DeliveryList;
import org.thinking.logistics.resource.service.DeliveryListService;

import java.util.List;

/**
 * Created by Henry on 2017/6/14.
 */
@RestController
@RequestMapping("/deliveryList")
public class DeliveryListController {
    private DeliveryListService deliveryListService;

    public DeliveryListController(DeliveryListService deliveryListService) {
        this.deliveryListService = deliveryListService;
    }

    @GetMapping("/findOne")
    public DeliveryList findOne(@QuerydslPredicate(root = DeliveryList.class) Predicate predicate) {
        return this.deliveryListService.findOne(predicate);
    }

    @GetMapping("/findAll")
    public List<DeliveryList> findAll(@QuerydslPredicate(root = DeliveryList.class) Predicate predicate) {
        return this.deliveryListService.findAll(predicate);
    }

    @PostMapping("/save")
    public void save(@RequestBody String data) {
        this.deliveryListService.save(data);
    }

    @GetMapping("/getFileName")
    public String getFileName(@QuerydslPredicate(root = DeliveryList.class) Predicate predicate) {
        return this.deliveryListService.getFileName(predicate);
    }
}