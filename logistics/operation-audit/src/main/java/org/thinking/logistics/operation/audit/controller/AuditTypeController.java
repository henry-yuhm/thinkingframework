package org.thinking.logistics.operation.audit.controller;

import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.*;
import org.thinking.logistics.operation.audit.domain.AuditType;
import org.thinking.logistics.operation.audit.service.AuditTypeService;

import java.util.List;

@RestController
@RequestMapping("/auditType")
public class AuditTypeController {
    private final AuditTypeService service;

    @Autowired
    public AuditTypeController(AuditTypeService service) {
        this.service = service;
    }

    @GetMapping("/findOne")
    public AuditType findOne(@QuerydslPredicate(root = AuditType.class) Predicate predicate) {
        return this.service.findOne(predicate);
    }

    @GetMapping("/findAll")
    public List<AuditType> findAll(@QuerydslPredicate(root = AuditType.class) Predicate predicate) {
        return this.service.findAll(predicate);
    }

    @PutMapping("/save")
    public void save(@RequestBody List<AuditType> types) throws Exception {
        this.service.save(types);
    }
}