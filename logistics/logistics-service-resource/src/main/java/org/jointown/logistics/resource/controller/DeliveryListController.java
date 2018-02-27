package org.jointown.logistics.resource.controller;

import com.querydsl.core.types.Predicate;
import org.jointown.logistics.resource.entity.DeliveryListEntity;
import org.jointown.logistics.resource.service.DeliveryListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Henry on 2017/6/14.
 */
@RestController
@RequestMapping("/deliveryList")
public class DeliveryListController {
    @Autowired
    private DeliveryListService deliveryListService;

    @GetMapping("/findOne")
    public DeliveryListEntity findOne(@QuerydslPredicate(root = DeliveryListEntity.class) Predicate predicate) {
        return this.deliveryListService.findOne(predicate);
    }

    @GetMapping("/findAll")
    public List<DeliveryListEntity> findAll(@QuerydslPredicate(root = DeliveryListEntity.class) Predicate predicate) {
        return this.deliveryListService.findAll(predicate);
    }

    @PostMapping("/save")
    public void save(@RequestBody String data) {
        this.deliveryListService.save(data);
    }

    @GetMapping("/getFileName")
    public String getFileName(@QuerydslPredicate(root = DeliveryListEntity.class) Predicate predicate) {
        return this.deliveryListService.getFileName(predicate);
    }
}