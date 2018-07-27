package org.thinking.sce.service.core.domain.barcode;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.sce.service.core.domain.common.Warehouse;
import org.thinking.sce.service.core.domain.support.BarcodeType;
import org.thinking.sce.service.core.domain.support.TransmissionType;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

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