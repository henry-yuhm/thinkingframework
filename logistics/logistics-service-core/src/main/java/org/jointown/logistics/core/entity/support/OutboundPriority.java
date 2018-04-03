package org.jointown.logistics.core.entity.support;

public enum OutboundPriority {
    GREEN_CHANNEL("10", 10),//绿色通道
    APPENDANT_PICKING("15", 15),//追加拣货
    SCANGUN_PROMOTION("19", 19),//条码枪提升
    SELF_SERVICE("20", 20),//自提
    CURRENT_AREA_PROMOTION("21", 21),//本区提升
    PREVIOUS_AREA_PROMOTION("22", 22),//前区提升
    RETURNED_PURCHASE("28", 28),//购进退出
    SELF_SERVICE_2_DELIVERY("30", 30),//自提转配送
    PASSIVITY_REPLENISHMENT("35", 35),//被动补货
    CONSIGNMENT("40", 40),//托运
    INNER_CITY_DELIVERY("50", 50),//市内配送
    SELF_SERVICE_STOCKUP("53", 53),//自提备货
    OUTER_CITY_DELIVERY("55", 55),//市外配送
    RETAIL_CHAINS("60", 60),//连锁
    INITIATIVE_REPLENISHMENT("65", 65),//主动补货
    OUTBOUND_FLITTING("70", 70),//调拨出库
    OUTBOUND_DEMONSTRATION("80", 80),//演示出库
    DEVICE_MANUAL_OPERATION("90", 90);//设备手动

    OutboundPriority(String name, int ordinal) {
    }
}