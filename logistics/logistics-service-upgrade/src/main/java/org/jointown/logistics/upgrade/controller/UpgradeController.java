package org.jointown.logistics.upgrade.controller;

import com.alibaba.fastjson.JSONObject;
import com.querydsl.core.types.Predicate;
import org.jointown.logistics.upgrade.entity.UpgradeConfigEntity;
import org.jointown.logistics.upgrade.service.UpgradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Henry on 2017/3/30.
 */
@RestController
@RequestMapping
public class UpgradeController {
    @Autowired
    private UpgradeService upgradeService;

    @GetMapping("/findOne")
    public UpgradeConfigEntity findOne(@QuerydslPredicate(root = UpgradeConfigEntity.class) Predicate predicate) {
        return this.upgradeService.findOne(predicate);
    }

    @GetMapping("/findAll")
    public List<UpgradeConfigEntity> findAll(@QuerydslPredicate(root = UpgradeConfigEntity.class) Predicate predicate) {
        return this.upgradeService.findAll(predicate);
    }

    @PostMapping("/save")
    public void save(@RequestBody String data) {
        this.upgradeService.save(JSONObject.parseObject(data, UpgradeConfigEntity.class));
    }

    @PostMapping("/upgradeAll")
    public void upgradeAll() {
        for (UpgradeConfigEntity upgradeConfigEntity : this.upgradeService.findAllByIsAvailableAndIsUpgradedOrderByUpgradeOrder("Y", "N")) {
            try {
                this.upgradeService.executeUpgrade(upgradeConfigEntity);
            } catch (Exception ex) {
                this.upgradeService.setUpgradeErrors(upgradeConfigEntity, ex);
                break;
            }
        }
    }
}