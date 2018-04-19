package org.thinking.logistics.operation.audit.controller;

import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.thinking.logistics.operation.audit.entity.AuditData;
import org.thinking.logistics.operation.audit.service.AuditDataService;
import org.thinking.logistics.services.core.domain.CompositeException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/auditData")
public class AuditDataController {
    private final AuditDataService service;

    @Autowired
    public AuditDataController(AuditDataService service) {
        this.service = service;
    }

    @GetMapping("/findOne")
    public AuditData findOne(@QuerydslPredicate(root = AuditData.class) Predicate predicate) {
        return this.service.findOne(predicate);
    }

    @GetMapping("/findAll")
    public List<AuditData> findAll(@QuerydslPredicate(root = AuditData.class) Predicate predicate) {
        return this.service.findAll(predicate);
    }

    @PutMapping("/save")
    public void save(@Valid @RequestBody List<AuditData> datas, BindingResult result) throws Exception {
        if (result.hasErrors()) {
            StringBuilder builder = new StringBuilder();
            result.getAllErrors().forEach(objectError -> builder.append(objectError.getDefaultMessage()));
            throw CompositeException.getException(builder.toString());
        }

        this.service.save(datas);
    }
}