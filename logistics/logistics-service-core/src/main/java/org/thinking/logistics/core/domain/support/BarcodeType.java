package org.thinking.logistics.core.domain.support;

public enum BarcodeType {
    INBOUND("1", 1),//入库
    OUTBOUND("2", 2),//出库
    REPLENISHING("3", 3),//补货
    TRANSFERRING("4", 4);//移库

    BarcodeType(String name, int ordinal) {
    }
}