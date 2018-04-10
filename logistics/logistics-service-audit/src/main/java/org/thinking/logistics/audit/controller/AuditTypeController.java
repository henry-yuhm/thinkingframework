package org.thinking.logistics.audit.controller;

import com.querydsl.core.types.Predicate;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.*;
import org.thinking.logistics.audit.entity.AuditType;
import org.thinking.logistics.audit.service.AuditTypeService;

import java.util.List;

@RestController
@RequestMapping("/auditType")
public class AuditTypeController {
    private AuditTypeService auditTypeService;

    public AuditTypeController(AuditTypeService auditTypeService) {
        this.auditTypeService = auditTypeService;
    }

    @GetMapping("/findOne")
    public AuditType findOne(@QuerydslPredicate(root = AuditType.class) Predicate predicate) {
        return this.auditTypeService.findOne(predicate);
    }

    @GetMapping("/findAll")
    public List<AuditType> findAll(@QuerydslPredicate(root = AuditType.class) Predicate predicate) {
        return this.auditTypeService.findAll(predicate);
    }

    @PutMapping("/save")
    public List<AuditType> save(@RequestBody List<AuditType> types) {
        return this.auditTypeService.save(types);
    }
}