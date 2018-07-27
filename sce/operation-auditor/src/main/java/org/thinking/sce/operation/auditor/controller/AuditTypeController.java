package org.thinking.sce.operation.auditor.controller;

import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thinking.sce.operation.auditor.domain.AuditType;
import org.thinking.sce.operation.auditor.service.AuditTypeService;

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