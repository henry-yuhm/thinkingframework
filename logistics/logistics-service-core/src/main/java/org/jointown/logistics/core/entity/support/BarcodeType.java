package org.jointown.logistics.core.entity.support;

public enum BarcodeType {
    INBOUND("1", 1),//入库
    OUTBOUND("2", 2),//出库
    REPLENISHMENT("3", 3),//补货
    MOVEMENT("4", 4);//移库

    BarcodeType(String name, int ordinal) {
    }
}