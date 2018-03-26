package org.jointown.logistics.common.entity;

import org.jointown.logistics.common.entity.support.BillCategory;
import org.jointown.logistics.common.entity.support.BillStage;
import org.jointown.logistics.common.entity.support.BillType;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;
import java.util.Set;

@MappedSuperclass
public abstract class BillHeader {
    @Id
    @GeneratedValue
    protected long id;

    @OneToOne(fetch = FetchType.LAZY)
    protected Warehouse warehouse;//仓库

    @OneToOne(fetch = FetchType.LAZY)
    protected Owner owner;//业主

    @OneToOne(fetch = FetchType.LAZY)
    protected Customer customer;//客户

    protected String billno;//单据编号

    protected BillType billType;//单据类型

    protected BillCategory billCategory;//单据类别

    protected BillStage billStage;//单据阶段

    protected String operator;//操作员

    protected boolean passback;//是否回传

    protected BigInteger printTimes;//打印次数

    protected Date creationTime;//创建时间

    protected Date modificationTime;//修改时间

    protected String remarks;//备注

    @ManyToMany
    protected Set<? extends BillDetail> details;//单据明细
}