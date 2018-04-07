package org.thinking.logistics.core.domain.support;

public enum TaskCategory {
    INVENTORY_CHECK("1", 1),//盘点
    GREEN_CHANNEL("2", 2),//绿色通道
    RETURNED_PURCHASE("3", 3),//购进退出
    OUTBOUND_SELF_SERVICE("4", 4),//自提出库
    OUTBOUND_NORMAL("5", 5),//普通出库
    PASSIVITY_REPLENISHING("6", 6),//被动补货
    INITIATIVE_REPLENISHING("7", 7),//主动补货
    INBOUND_UPSHELF("9", 9),//入库上架
    TRANSFERRING("10", 10),//移库
    OUTBOUND_GIFT("11", 11),//赠品出库
    ADJUST_BATCH("12", 12);//批号调整

    TaskCategory(String name, int ordinal) {
    }
}