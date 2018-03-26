package org.jointown.logistics.common.entity.bill;

import org.jointown.logistics.common.entity.Customer;
import org.jointown.logistics.common.entity.Owner;
import org.jointown.logistics.common.entity.Warehouse;
import org.jointown.logistics.common.entity.support.BillCategory;
import org.jointown.logistics.common.entity.support.BillPriority;
import org.jointown.logistics.common.entity.support.BillSign;
import org.jointown.logistics.common.entity.support.BillType;

import javax.persistence.*;
import java.sql.Time;

@MappedSuperclass
public abstract class BillHeader {
    @Id
    @GeneratedValue
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    private Warehouse warehouse;//仓库

    @OneToOne(fetch = FetchType.LAZY)
    private Owner owner;//业主

    @OneToOne(fetch = FetchType.LAZY)
    private Customer customer;//客户

    private String no;//单据编号

    private BillType type;//单据类型

    private BillCategory category;//单据类别

    private BillSign sign;//单据标识

    private BillPriority priority;//单据优先级

    private String operator;//操作员

    private Time creationTime;//创建时间

    private Time modificationTime;//修改时间

    private String remarks;//备注
}