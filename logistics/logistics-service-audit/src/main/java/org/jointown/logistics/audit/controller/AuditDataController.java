package org.jointown.logistics.audit.controller;

import com.querydsl.core.types.Predicate;
import org.jointown.logistics.audit.entity.AuditData;
import org.jointown.logistics.audit.service.AuditDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auditData")
public class AuditDataController {
    @Autowired
    private AuditDataService auditDataService;

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