package org.thinking.logistics.core.entity.bill;

import lombok.Data;
import org.thinking.logistics.core.domain.support.BillCategory;
import org.thinking.logistics.core.domain.support.BillKind;
import org.thinking.logistics.core.domain.support.BillSource;
import org.thinking.logistics.core.entity.Employee;
import org.thinking.logistics.core.entity.Owner;
import org.thinking.logistics.core.entity.Warehouse;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Set;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public abstract class Header {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Warehouse warehouse;//仓库

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Owner owner;//业主

    @Column(unique = true, nullable = false, length = 100)
    private String number;//单据编号

    @Column(nullable = false)
    private BillKind kind;//单据类型

    @Column(nullable = false)
    private BillCategory category;//单据类别

    @Column(nullable = false)
    private BillSource source;//单据来源

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Employee operator;//操作员

    @ManyToOne(fetch = FetchType.LAZY)
    private Employee businessman;//业务员

    @Column(nullable = false)
    private Date creationTime = Date.valueOf(LocalDate.now());//创建时间

    @Column(nullable = false)
    private Date modificationTime = Date.valueOf(LocalDate.now());//修改时间

    private String remarks;//备注

    public abstract Set<? extends Detail> getDetails();
}