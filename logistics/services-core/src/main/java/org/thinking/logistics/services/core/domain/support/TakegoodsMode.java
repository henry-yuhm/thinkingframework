package org.thinking.logistics.services.core.domain.support;

//提货方式
public enum TakegoodsMode {
    NONE("0", 0),
    SELF_SERVICE("1", 1),//自提
    SELF_SERVICE_STOCKUP("2", 2),//自提备货
    CONSIGNMENT("3", 3),//托运
    URBAN_DISTRIBUTION("4", 4),//市内配送
    SUBURBAN_DISTRIBUTION("5", 5),//市外配送
    FLITTING("6", 6),//调拨
    GREEN_CHANNEL("7", 7),//绿色通道
    SELF_SERVICE_2_DISTRIBUTION("8", 8),//自提转配送
    RETAIL_CHAINS("9", 9);//连锁

    TakegoodsMode(String name, int ordinal) {
    }
}