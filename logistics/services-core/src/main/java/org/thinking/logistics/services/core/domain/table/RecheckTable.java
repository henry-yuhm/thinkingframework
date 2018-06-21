package org.thinking.logistics.services.core.domain.table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.services.core.domain.BaseDomainEntity;
import org.thinking.logistics.services.core.domain.core.Warehouse;
import org.thinking.logistics.services.core.domain.support.RecheckTableCategory;
import org.thinking.logistics.services.core.domain.support.RecheckTableType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.sql.Date;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class RecheckTable extends BaseDomainEntity {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Warehouse warehouse;//仓库

    @Column(nullable = false)
    private String no;//编号

    @Column(nullable = false)
    private RecheckTableType type = RecheckTableType.NORMAL;//类型

    @Column(nullable = false)
    private RecheckTableCategory category;//类别

    @Column(nullable = false)
    private boolean locking = false;//锁定

    @Column(nullable = false)
    private boolean automatic;//自动化

    @Column(nullable = false)
    private int workload = 0;//工作量

    @Column(nullable = false)
    private int itemQuantity = 0;//品规数

    private Date modificationTime;//修改时间
}