package org.thinking.logistics.services.core.entity.barcode;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.services.core.domain.support.BarcodeKind;
import org.thinking.logistics.services.core.domain.support.TransmissionKind;
import org.thinking.logistics.services.core.entity.Warehouse;

import javax.persistence.*;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class TaskBarcode extends Barcode {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Warehouse warehouse;//仓库

    @Column(nullable = false)
    private BarcodeKind kind;//条码类型

    @Column(nullable = false)
    private String deviceNo;//设备编号

    @Column(nullable = false)
    private TransmissionKind transmissionKind;//传输类型
}