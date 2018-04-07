package org.thinking.logistics.stock.controller;

import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.*;
import org.thinking.logistics.stock.entity.Invoice;
import org.thinking.logistics.stock.service.InvoicingService;

import java.util.List;

@RestController
@RequestMapping("/invoicing")
public class InvoicingController {
    @Autowired
    private InvoicingService invoicingService;

    @GetMapping("/findOne")
    public Invoice findOne(@QuerydslPredicate(root = Invoice.class) Predicate predicate) {
        return this.invoicingService.findOne(predicate);
    }

    @GetMapping("/findAll")
    public List<Invoice> findAll(@QuerydslPredicate(root = Invoice.class) Predicate predicate) {
        return this.invoicingService.findAll(predicate);
    }

    @PostMapping("/save")
    public String save(@RequestBody String data) {
        return this.invoicingService.save(data);
    }
}