package org.thinking.logistics.services.core.domain.document;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.thinking.logistics.services.core.domain.support.ArrivalMode;
import org.thinking.logistics.services.core.domain.support.ArrivalVoucher;
import org.thinking.logistics.services.core.domain.support.InboundStage;

import javax.persistence.*;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@DynamicInsert
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper = true)
public class ReceivingNoteHeader extends Header {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private PurchaseOrderHeader order;//订单

    @Column(nullable = false)
    private InboundStage stage = InboundStage.CREATED;//入库阶段

    @Column(nullable = false)
    private String receivingClerk;//收货员

    private String auditor;//审核员

    private String inspector;//质检员

    @Column(nullable = false)
    private boolean passback;//回传

    @Column(nullable = false)
    private boolean chargeup;//记账

    @Column(nullable = false)
    private boolean executed;//执行

    @Column(nullable = false)
    private boolean completed;//完成

    private ArrivalMode arrivalMode;//到货方式

    @Column(nullable = false)
    private ArrivalVoucher arrivalVoucher = ArrivalVoucher.HOLD;//到货凭证

    private String arrivalNo;//到货单号

    @Temporal(TemporalType.DATE)
    private Instant arrivalTime;//到货时间

    private String saleOrderNo;//销售订单号

    @Column(nullable = false)
    private boolean printed = false;//打印

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(joinColumns = @JoinColumn(name = "header_id"), inverseJoinColumns = @JoinColumn(name = "detail_id"))
    private Set<ReceivingNoteDetail> details = new LinkedHashSet<>();//单据明细
}