package org.thinking.logistics.core.domain.support;

public enum BusinessType {
    INBOUND_PURCHASE("1", 1),//购进入库
    OUTBOUND_SALE("2", 2),//销售出库
    RETURNED_PURCHASE("3", 3),//购进退出
    RETURNED_SALE("4", 4),//销售退回
    REPLENISHING("5", 5),//补货
    DEMONSTRATION("6", 6),//演示作业
    TRANSFERRING("7", 7),//移库
    RETURNED_PALLET("8", 8),//回盘
    INVENTORY_CHECK("9", 9),//盘点
    OUTBOUND_GIFT("10", 10),//赠品出库
    ADJUST_BATCH("11", 11);//批号调整

    BusinessType(String name, int ordinal) {
    }
}