package org.thinking.logistics.core.entity.table;

import lombok.Data;
import org.thinking.logistics.core.domain.support.RecheckTableCategory;
import org.thinking.logistics.core.domain.support.RecheckTableKind;
import org.thinking.logistics.core.entity.Warehouse;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
public class RecheckTable {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Warehouse warehouse;//仓库

    @Column(nullable = false)
    private String number;//编号

    @Column(nullable = false)
    private RecheckTableKind kind = RecheckTableKind.NORMAL;//类型

    @Column(nullable = false)
    private RecheckTableCategory category;//类别

    @Column(nullable = false)
    private boolean locking = false;//锁定

    @Column(nullable = false)
    private boolean automatic;//自动化

    @Column(nullable = false)
    private int workload = 0;//工作量

    @Column(nullable = false)
    private int goodsQuantity = 0;//品规数

    private Date modificationTime;//修改时间
}