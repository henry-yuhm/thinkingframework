package org.thinking.logistics.audit.controller;

import com.querydsl.core.types.Predicate;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.*;
import org.thinking.logistics.audit.entity.AuditData;
import org.thinking.logistics.audit.service.AuditDataService;

import java.util.List;

@RestController
@RequestMapping("/auditData")
public class AuditDataController {
    private AuditDataService auditDataService;

    public AuditDataController(AuditDataService auditDataService) {
        this.auditDataService = auditDataService;
    }

    @GetMapping("/findOne")
    public AuditData findOne(@QuerydslPredicate(root = AuditData.class) Predicate predicate) {
        return this.auditDataService.findOne(predicate);
    }

    @GetMapping("/findAll")
    public List<AuditData> findAll(@QuerydslPredicate(root = AuditData.class) Predicate predicate) {
        return this.auditDataService.findAll(predicate);
    }

    @PutMapping("/save")
    public List<AuditData> save(@RequestBody List<AuditData> datas) {
        return this.auditDataService.save(datas);
    }
}