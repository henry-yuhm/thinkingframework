package org.jointown.logistics.stock.controller;

import com.querydsl.core.types.Predicate;
import org.jointown.logistics.stock.entity.InvoicingEntity;
import org.jointown.logistics.stock.service.InvoicingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoicing")
public class InvoicingController {
    @Autowired
    private InvoicingService invoicingService;

    @GetMapping("/findOne")
    public InvoicingEntity findOne(@QuerydslPredicate(root = InvoicingEntity.class) Predicate predicate) {
        return this.invoicingService.findOne(predicate);
    }

    @GetMapping("/findAll")
    public List<InvoicingEntity> findAll(@QuerydslPredicate(root = InvoicingEntity.class) Predicate predicate) {
        return this.invoicingService.findAll(predicate);
    }

    @PostMapping("/save")
    public String save(@RequestBody String data) {
        return this.invoicingService.save(data);
    }
}