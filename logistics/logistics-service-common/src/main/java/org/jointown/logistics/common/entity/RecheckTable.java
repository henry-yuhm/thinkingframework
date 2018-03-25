package org.jointown.logistics.common.entity;

import org.jointown.logistics.common.entity.support.BufferType;
import org.jointown.logistics.common.entity.support.TableCategory;
import org.jointown.logistics.common.entity.support.TableType;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

@Entity
public class RecheckTable {
    @Id
    @TableGenerator(name = "RecheckTableId", table = "RecheckTableId", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "RecheckTableId")
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_rct_wh"))
    private Warehouse warehouse;//仓库

    private String tableNo;//复核台编号

    private TableType tableType;//复核台类型

    private TableCategory tableCategory;//复核台类别

    private boolean available;//是否可用

    private boolean locking;//是否锁定

    private String bufferNo;//暂存位编号

    private BufferType bufferType;//暂存位类型

    private boolean automatic;//是否自动化

    private String slideNo;//滑道编号

    @Column(nullable = false)
    private BigInteger workload = BigInteger.ZERO;//工作量

    @Column(nullable = false)
    private BigInteger goodsNumber = BigInteger.ZERO;//品规数

    private TaskBill taskBill;//任务单

    private String splitBillNo;//拆分单编号

    private Date modificationTime;//修改时间
}