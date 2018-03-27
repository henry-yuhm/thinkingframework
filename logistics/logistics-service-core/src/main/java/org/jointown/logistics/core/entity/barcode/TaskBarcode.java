package org.jointown.logistics.core.entity.barcode;

import org.jointown.logistics.core.entity.Warehouse;
import org.jointown.logistics.core.entity.support.BarcodeType;
import org.jointown.logistics.core.entity.support.TransferlineSign;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

@MappedSuperclass
public abstract class TaskBarcode extends Barcode {
    @OneToOne(fetch = FetchType.LAZY)
    @Column(nullable = false)
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