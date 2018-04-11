package org.thinking.logistics.audit.controller;

import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.*;
import org.thinking.logistics.audit.entity.AuditKind;
import org.thinking.logistics.audit.service.AuditKindService;

import java.util.List;

@RestController
@RequestMapping("/auditKind")
public class AuditKindController {
    private final AuditKindService auditKindService;

    @Autowired
    public AuditKindController(AuditKindService auditKindService) {
        this.auditKindService = auditKindService;
    }

    @GetMapping("/findOne")
    public AuditKind findOne(@QuerydslPredicate(root = AuditKind.class) Predicate predicate) {
        return this.auditKindService.findOne(predicate);
    }

    @GetMapping("/findAll")
    public List<AuditKind> findAll(@QuerydslPredicate(root = AuditKind.class) Predicate predicate) {
        return this.auditKindService.findAll(predicate);
    }

    @PutMapping("/save")
    public void save(@RequestBody List<AuditKind> kinds) throws Exception {
        this.auditKindService.save(kinds);
    }
}