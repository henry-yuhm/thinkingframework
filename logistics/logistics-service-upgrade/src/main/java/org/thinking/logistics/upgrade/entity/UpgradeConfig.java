package org.thinking.logistics.upgrade.entity;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * Created by Henry on 2017/3/30.
 */
@Entity
@Table(name = "UPGRADE_CONFIG")
public class UpgradeConfig {
    @Id
    @Column(name = "UPGRADE_OBJECT")
    @JSONField(name = "UpgradeObject")
    private String upgradeObject;

    @Column(name = "UPGRADE_ORDER")
    @JSONField(ordinal = 1,
            name = "UpgradeOrder")
    private BigDecimal upgradeOrder;

    @Column(name = "IS_AVAILABLE")
    @JSONField(ordinal = 2,
            name = "IsAvailable")
    private String isAvailable;

    @Column(name = "PRE_UPGRADE")
    @JSONField(ordinal = 3,
            name = "preUpgrade")
    private String preUpgrade;

    @Column(name = "IS_UPGRADED")
    @JSONField(ordinal = 4,
            name = "IsUpgraded")
    private String isUpgraded;

    @Column(name = "UPGRADE_SCRIPT")
    @JSONField(ordinal = 5,
            name = "UpgradeScript")
    private String upgradeScript;

    @Column(name = "WANTED_ROWS")
    @JSONField(ordinal = 6,
            name = "WantedRows")
    private BigDecimal wantedRows;

    @Column(name = "UPGRADED_ROWS")
    @JSONField(ordinal = 7,
            name = "UpgradedRows")
    private BigDecimal upgradedRows;

    @Column(name = "REMARKS")
    @JSONField(ordinal = 8,
            name = "Remarks")
    private String remarks;

    @Column(name = "ERRORS")
    @JSONField(ordinal = 9,
            name = "Errors")
    private String errors;

    public UpgradeConfig() {
    }

    public String getUpgradeObject() {
        return upgradeObject;
    }

    public void setUpgradeObject(String upgradeObject) {
        this.upgradeObject = upgradeObject;
    }

    public BigDecimal getUpgradeOrder() {
        return upgradeOrder;
    }

    public void setUpgradeOrder(BigDecimal upgradeOrder) {
        this.upgradeOrder = upgradeOrder;
    }

    public String getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(String isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String getPreUpgrade() {
        return preUpgrade;
    }

    public void setPreUpgrade(String preUpgrade) {
        this.preUpgrade = preUpgrade;
    }

    public String getIsUpgraded() {
        return isUpgraded;
    }

    public void setIsUpgraded(String isUpgraded) {
        this.isUpgraded = isUpgraded;
    }

    public String getUpgradeScript() {
        return upgradeScript;
    }

    public void setUpgradeScript(String upgradeScript) {
        this.upgradeScript = upgradeScript;
    }

    public BigDecimal getWantedRows() {
        return wantedRows;
    }

    public void setWantedRows(BigDecimal wantedRows) {
        this.wantedRows = wantedRows;
    }

    public BigDecimal getUpgradedRows() {
        return upgradedRows;
    }

    public void setUpgradedRows(BigDecimal upgradedRows) {
        this.upgradedRows = upgradedRows;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }
}
