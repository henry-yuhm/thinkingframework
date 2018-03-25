package org.jointown.logistics.common.entity.support;

public enum BillType {
    INBOUND_PURCHASE("1", 1),//购进入库
    OUTBOUND_SALE("2", 2),//销售出库
    PURCHASE_RETURN("3", 3),//购进退出
    SALE_RETURN("4", 4),//销售退回
    INITIATIVE_REPLENISHMENT("5", 5),//主动补货
    EMERGENCY_REPLENISHMENT("6", 6),//紧急补货
    LOCATION_MOVEMENT("7", 7),//移库
    LOCATION_ADJUST("8", 8);//货位调整

    BillType(String name, int ordinal) {
    }
}