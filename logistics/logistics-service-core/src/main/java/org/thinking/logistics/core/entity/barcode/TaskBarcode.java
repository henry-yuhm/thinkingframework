package org.thinking.logistics.core.entity.barcode;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.core.domain.support.BarcodeType;
import org.thinking.logistics.core.domain.support.TransferlineSign;
import org.thinking.logistics.core.entity.Warehouse;

import javax.persistence.*;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class TaskBarcode extends Barcode {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Warehouse warehouse;//仓库

    @Column(nullable = false)
    private BarcodeType type;//条码类型

    @Column(nullable = false)
    private String deviceNo;//设备编号

    @Column(nullable = false)
    private TransferlineSign sign;//输送线标识
}