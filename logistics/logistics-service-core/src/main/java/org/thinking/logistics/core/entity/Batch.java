package org.thinking.logistics.core.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(schema = "wms")
@Data
public class Batch {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Owner owner;//业主

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Goods goods;//商品

    @Column(nullable = false)
    private String number;//编码

    @Column(nullable = false)
    private Date productionDate;//生产日期

    @Column(nullable = false)
    private Date validUntil;//有效期至

    private String printProductionDate;//打印生产日期

    private String printValidUntil;//打印有效期至

    private String approvalNo;//批准文号
}