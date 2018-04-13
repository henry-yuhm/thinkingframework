package org.thinking.logistics.core.domain.support;

//单据类型
public enum BillKind {
    PURCHASE_INBOUND("11", 11),//采购入库
    SALE_OUTBOUND("12", 12),//销售出库
    PURCHASE_RETURN("13", 13),//采购退货
    SALE_RETURN("14", 14),//销售退回
    INITIATIVE_REPLENISHING("21", 21),//主动补货
    EMERGENCY_REPLENISHING("22", 22),//紧急补货
    TRANSFERRING("31", 31),//移库
    LOCATION_ADJUST("32", 32),//货位调整
    INVENTORY_CHECK("41", 41),//盘点
    DISTRIBUTION("51", 51);//配送

    BillKind(String name, int ordinal) {
    }
}