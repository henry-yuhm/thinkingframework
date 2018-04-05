package org.jointown.logistics.core.entity.support;

public enum BillType {
    INBOUND_PURCHASE("1", 1),//购进入库
    OUTBOUND_SALE("2", 2),//销售出库
    RETURNED_PURCHASE("3", 3),//购进退出
    RETURNED_SALE("4", 4),//销售退回
    INITIATIVE_REPLENISHMENT("5", 5),//主动补货
    EMERGENCY_REPLENISHMENT("6", 6),//紧急补货
    TRANSFERRING("7", 7),//移库
    LOCATION_ADJUST("8", 8),//货位调整
    DISTRIBUTION("21", 21);//配送

    BillType(String name, int ordinal) {
    }
}