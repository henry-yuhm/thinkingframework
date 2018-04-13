package org.thinking.logistics.operation.audit.controller;

import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.*;
import org.thinking.logistics.operation.audit.entity.AuditKind;
import org.thinking.logistics.operation.audit.service.AuditKindService;

import java.util.List;

@RestController
@RequestMapping("/auditKind")
public class AuditKindController {
    private final AuditKindService service;

    @Autowired
    public AuditKindController(AuditKindService service) {
        this.service = service;
    }

    @GetMapping("/findOne")
    public AuditKind findOne(@QuerydslPredicate(root = AuditKind.class) Predicate predicate) {
        return this.service.findOne(predicate);
    }

    @GetMapping("/findAll")
    public List<AuditKind> findAll(@QuerydslPredicate(root = AuditKind.class) Predicate predicate) {
        return this.service.findAll(predicate);
    }

    @PutMapping("/save")
    public void save(@RequestBody List<AuditKind> kinds) throws Exception {
        this.service.save(kinds);
    }
}