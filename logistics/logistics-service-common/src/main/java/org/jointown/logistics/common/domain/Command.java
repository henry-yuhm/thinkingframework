package org.jointown.logistics.common.domain;

public interface Command {
    enum BusinessType {
        INBOUND_PURCHASE("1", 1),//购进入库
        OUTBOUND_SALE("2", 2),//销售出库
        PURCHASE_RETURN("3", 3),//购进退出
        SALE_RETURN("4", 4),//销售退回
        REPLENISHMENT("5", 5),//补货
        DEMONSTRATION_OPERATION("6", 6),//演示作业
        LOCATION_MOVE("7", 7),//移库
        PALLET_RETURN("8", 8),//回盘
        STOCK_TAKING("9", 9),//盘点
        OUTBOUND_GIFT("10", 10),//赠品出库
        BATCHNUMBER_ADJUST("11", 11);//批号调整

        private final String name;

        private final int ordinal;

        BusinessType(String name, int ordinal) {
            this.name = name;
            this.ordinal = ordinal;
        }
    }
}