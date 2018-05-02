package org.thinking.logistics.upgrade.service.controller;

import com.alibaba.fastjson.JSONObject;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.*;
import org.thinking.logistics.upgrade.service.domain.UpgradeConfig;
import org.thinking.logistics.upgrade.service.service.UpgradeService;

import java.util.List;

/**
 * Created by Henry on 2017/3/30.
 */
@RestController
@RequestMapping
public class UpgradeController {
    private UpgradeService service;

    @Autowired
    public UpgradeController(UpgradeService service) {
        this.service = service;
    }

    @GetMapping("/findOne")
    public UpgradeConfig findOne(@QuerydslPredicate(root = UpgradeConfig.class) Predicate predicate) {
        return this.service.findOne(predicate);
    }

    @GetMapping("/findAll")
    public List<UpgradeConfig> findAll(@QuerydslPredicate(root = UpgradeConfig.class) Predicate predicate) {
        return this.service.findAll(predicate);
    }

    @PostMapping("/save")
    public void save(@RequestBody String data) {
        this.service.save(JSONObject.parseObject(data, UpgradeConfig.class));
    }

    @PostMapping("/upgradeAll")
    public void upgradeAll() {
        for (UpgradeConfig upgradeConfig : this.service.findAllByIsAvailableAndIsUpgradedOrderByUpgradeOrder("Y", "N")) {
            try {
                this.service.executeUpgrade(upgradeConfig);
            } catch (Exception ex) {
                this.service.setUpgradeErrors(upgradeConfig, ex);
                break;
            }
        }
    }
}