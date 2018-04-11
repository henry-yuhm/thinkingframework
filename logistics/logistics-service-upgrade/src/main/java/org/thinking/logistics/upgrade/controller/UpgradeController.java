package org.thinking.logistics.upgrade.controller;

import com.alibaba.fastjson.JSONObject;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.*;
import org.thinking.logistics.upgrade.entity.UpgradeConfig;
import org.thinking.logistics.upgrade.service.UpgradeService;

import java.util.List;

/**
 * Created by Henry on 2017/3/30.
 */
@RestController
@RequestMapping
public class UpgradeController {
    private UpgradeService upgradeService;

    @Autowired
    public UpgradeController(UpgradeService upgradeService) {
        this.upgradeService = upgradeService;
    }

    @GetMapping("/findOne")
    public UpgradeConfig findOne(@QuerydslPredicate(root = UpgradeConfig.class) Predicate predicate) {
        return this.upgradeService.findOne(predicate);
    }

    @GetMapping("/findAll")
    public List<UpgradeConfig> findAll(@QuerydslPredicate(root = UpgradeConfig.class) Predicate predicate) {
        return this.upgradeService.findAll(predicate);
    }

    @PostMapping("/save")
    public void save(@RequestBody String data) {
        this.upgradeService.save(JSONObject.parseObject(data, UpgradeConfig.class));
    }

    @PostMapping("/upgradeAll")
    public void upgradeAll() {
        for (UpgradeConfig upgradeConfig : this.upgradeService.findAllByIsAvailableAndIsUpgradedOrderByUpgradeOrder("Y", "N")) {
            try {
                this.upgradeService.executeUpgrade(upgradeConfig);
            } catch (Exception ex) {
                this.upgradeService.setUpgradeErrors(upgradeConfig, ex);
                break;
            }
        }
    }
}