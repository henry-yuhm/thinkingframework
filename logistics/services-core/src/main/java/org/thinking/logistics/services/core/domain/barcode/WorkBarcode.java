package org.thinking.logistics.services.core.domain.barcode;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.services.core.domain.common.Warehouse;
import org.thinking.logistics.services.core.domain.support.BarcodeType;
import org.thinking.logistics.services.core.domain.support.TransmissionType;

import javax.persistence.*;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class WorkBarcode extends Barcode {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Warehouse warehouse;//仓库

    @Column(nullable = false)
    private BarcodeType barcodeType;//条码类型

    @Column(nullable = false, updatable = false, length = 2)
    private String deviceNo;//设备编号

    @Column(nullable = false)
    private TransmissionType transmissionType;//传输类型
}