package org.jointown.logistics.core.entity.support;

public enum TaskCategory {
    INVENTORY_CHECK("1", 1),//盘点
    GREEN_CHANNEL("2", 2),//绿色通道
    RETURNED_PURCHASE("3", 3),//购进退出
    SELF_SERVICE_OUTBOUND("4", 4),//自提出库
    NORMAL_OUTBOUND("5", 5),//普通出库
    PASSIVITY_REPLENISHMENT("6", 6),//被动补货
    INITIATIVE_REPLENISHMENT("7", 7),//主动补货
    INBOUND_UPSHELF("9", 9),//入库上架
    LOCATION_MOVEMENT("10", 10),//移库
    GIFT_OUTBOUND("11", 11),//赠品出库
    BATCHNUMBER_ADJUST("12", 12);//批号调整

    TaskCategory(String name, int ordinal) {
    }
}