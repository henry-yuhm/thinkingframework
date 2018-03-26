package org.jointown.logistics.common.entity.barcode;

import org.jointown.logistics.common.entity.Warehouse;
import org.jointown.logistics.common.entity.support.BarcodeType;
import org.jointown.logistics.common.entity.support.TransferlineSign;

import javax.persistence.FetchType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

@MappedSuperclass
public abstract class OperationBarcode extends Barcode {
    @OneToOne(fetch = FetchType.LAZY)
    private Warehouse warehouse;//仓库

    private BarcodeType barcodeType;//条码类型

    private String deviceNo;//设备编号

    private TransferlineSign transferlineSign;//输送线标识

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public BarcodeType getBarcodeType() {
        return barcodeType;
    }

    public void setBarcodeType(BarcodeType barcodeType) {
        this.barcodeType = barcodeType;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public TransferlineSign getTransferlineSign() {
        return transferlineSign;
    }

    public void setTransferlineSign(TransferlineSign transferlineSign) {
        this.transferlineSign = transferlineSign;
    }
}