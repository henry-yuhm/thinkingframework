package org.thinking.logistics.core.domain.support;

//条码类型
public enum BarcodeKind {
    INBOUND("1", 1),//入库
    OUTBOUND("2", 2),//出库
    REPLENISHING("3", 3),//补货
    TRANSFERRING("4", 4);//移库

    BarcodeKind(String name, int ordinal) {
    }
}