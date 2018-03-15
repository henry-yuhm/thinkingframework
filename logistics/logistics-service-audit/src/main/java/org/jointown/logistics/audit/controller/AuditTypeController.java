package org.jointown.logistics.audit.controller;

import com.querydsl.core.types.Predicate;
import org.jointown.logistics.audit.entity.AuditType;
import org.jointown.logistics.audit.service.AuditTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auditType")
public class AuditTypeController {
    @Autowired
    private AuditTypeService auditTypeService;

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