package org.thinking.logistics.services.core.domain.support;

//指令类型
public enum CommandType {
    PURCHASE_INBOUND("1", 1),//采购入库
    SALE_OUTBOUND("2", 2),//销售出库
    PURCHASE_RETURN("3", 3),//采购退货
    SALE_RETURN("4", 4),//销售退回
    REPLENISHING("5", 5),//补货
    DEMONSTRATION("6", 6),//演示作业
    TRANSFERRING("7", 7),//移库
    PALLET_RETURN("8", 8),//回盘
    INVENTORY_CHECK("9", 9),//盘点
    GIFT_OUTBOUND("10", 10),//赠品出库
    BATCH_ADJUST("11", 11);//批号调整

    CommandType(String name, int ordinal) {
    }
}