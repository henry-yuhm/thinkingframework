package org.thinking.logistics.services.core.domain.support;

//指令类别
public enum CommandCategory {
    INVENTORY_CHECK("1", 1),//盘点
    GREEN_CHANNEL("2", 2),//绿色通道
    PURCHASE_RETURN("3", 3),//购进退出
    SELF_SERVICE_OUTBOUND("4", 4),//自提出库
    NORMAL_OUTBOUND("5", 5),//普通出库
    PASSIVITY_REPLENISHING("6", 6),//被动补货
    INITIATIVE_REPLENISHING("7", 7),//主动补货
    UPSHELF("9", 9),//入库上架
    TRANSFERRING("10", 10),//移库
    GIFT_OUTBOUND("11", 11),//赠品出库
    BATCH_ADJUST("12", 12);//批号调整

    CommandCategory(String name, int ordinal) {
    }
}