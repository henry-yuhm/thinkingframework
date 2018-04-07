package org.thinking.logistics.core.entity.barcode;

import org.thinking.logistics.core.domain.support.BarcodeType;
import org.thinking.logistics.core.domain.support.TransferlineSign;
import org.thinking.logistics.core.entity.Warehouse;

import javax.persistence.*;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class TaskBarcode extends Barcode {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Warehouse warehouse;//仓库

    @Column(nullable = false)
    private BarcodeType type;//条码类型

    @Column(nullable = false)
    private String deviceNo;//设备编号

    @Column(nullable = false)
    private TransferlineSign sign;//输送线标识

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public BarcodeType getType() {
        return type;
    }

    public void setType(BarcodeType type) {
        this.type = type;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public TransferlineSign getSign() {
        return sign;
    }

    public void setSign(TransferlineSign sign) {
        this.sign = sign;
    }
}