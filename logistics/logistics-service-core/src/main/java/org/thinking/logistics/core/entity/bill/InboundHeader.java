package org.thinking.logistics.core.entity.bill;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.core.domain.support.ArrivalMode;
import org.thinking.logistics.core.domain.support.ArrivalVoucher;
import org.thinking.logistics.core.domain.support.InboundStage;

import javax.persistence.*;
import java.sql.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class InboundHeader extends Header {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private PurchaseOrderHeader order;//订单

    @Column(nullable = false)
    private InboundStage stage = InboundStage.CREATED;//入库阶段

    @Column(nullable = false)
    private String receivingClerk;//收货员

    private String auditor;//审核员

    private String inspector;//质检员

    @Column(nullable = false)
    private boolean passback = true;//回传

    @Column(nullable = false)
    private boolean chargeup = false;//记账

    @Column(nullable = false)
    private boolean executed = false;//执行

    @Column(nullable = false)
    private boolean complete = false;//完成

    private ArrivalMode arrivalMode;//到货方式

    @Column(nullable = false)
    private ArrivalVoucher arrivalVoucher = ArrivalVoucher.HOLD;//到货凭证

    private String arrivalNo;//到货单号

    private Date arrivalTime;//到货时间

    private String saleOrderNo;//销售订单号

    @Column(nullable = false)
    private boolean printed = false;//打印

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "header_id"), inverseJoinColumns = @JoinColumn(name = "detail_id"))
    private Set<InboundDetail> details = new LinkedHashSet<>();//单据明细
}