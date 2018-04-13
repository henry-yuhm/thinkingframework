package org.thinking.logistics.upgrade.service.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * Created by Henry on 2017/3/30.
 */
@Entity
@Data
public class UpgradeConfig {
    @Id
    @JSONField
    private String upgradeObject;

    @JSONField(ordinal = 1)
    private BigDecimal upgradeOrder;

    @JSONField(ordinal = 2)
    private boolean available;

    @JSONField(ordinal = 3)
    private String preUpgrade;

    @JSONField(ordinal = 4)
    private boolean upgraded;

    @JSONField(ordinal = 5)
    private String upgradeScript;

    @JSONField(ordinal = 6)
    private BigDecimal wantedRows;

    @JSONField(ordinal = 7)
    private BigDecimal upgradedRows;

    @JSONField(ordinal = 8)
    private String remarks;

    @JSONField(ordinal = 9)
    private String errors;
}
