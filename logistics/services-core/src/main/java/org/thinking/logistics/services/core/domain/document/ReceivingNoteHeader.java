package org.thinking.logistics.services.core.domain.document;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.thinking.logistics.services.core.domain.employee.Employee;
import org.thinking.logistics.services.core.domain.support.ArrivalMode;
import org.thinking.logistics.services.core.domain.support.ArrivalVoucher;
import org.thinking.logistics.services.core.domain.support.ReceivingStatus;

import javax.persistence.*;
import java.time.Instant;
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
    private ReceivingStatus receivingStatus;//收货状态

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Employee receivingClerk;//收货员

    @ManyToOne(fetch = FetchType.LAZY)
    private Employee auditor;//审核员

    @ManyToOne(fetch = FetchType.LAZY)
    private Employee inspector;//质检员

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
    private ArrivalVoucher arrivalVoucher;//到货凭证

    private String arrivalNo;//到货单号

    @Temporal(TemporalType.DATE)
    private Instant arrivalTime;//到货时间

    private String saleOrderNo;//销售订单号

    @Column(nullable = false)
    private boolean printed;//打印

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(joinColumns = @JoinColumn(name = "header_id"), inverseJoinColumns = @JoinColumn(name = "detail_id"))
    private Set<ReceivingNoteDetail> details;//单据明细
}