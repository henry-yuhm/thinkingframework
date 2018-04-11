package org.thinking.logistics.audit.controller;

import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.*;
import org.thinking.logistics.audit.entity.AuditData;
import org.thinking.logistics.audit.service.AuditDataService;

import java.util.List;

@RestController
@RequestMapping("/auditData")
public class AuditDataController {
    private final AuditDataService auditDataService;

    @Autowired
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
    public void save(@RequestBody List<AuditData> datas) throws Exception {
        this.auditDataService.save(datas);
    }
}