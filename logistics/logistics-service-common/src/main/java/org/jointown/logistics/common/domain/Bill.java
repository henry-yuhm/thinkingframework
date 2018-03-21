package org.jointown.logistics.common.domain;

public interface Bill {
    enum Category {
        WESTERN_MEDICINE("1", 1),//西药
        TRADITIONAL_CHINESE_MEDICINE("2", 2),//中药
        FAMILY_PLANNING("3", 3),//计生
        APPLIANCE("4", 4),//器械
        RAW_MATERIAL("5", 5),//原料药
        THIRD_PARTY("6", 6),//第三方
        GIFT("7", 7);//赠品

        private final String name;

        private final int ordinal;

        Category(String name, int ordinal) {
            this.name = name;
            this.ordinal = ordinal;
        }
    }

    enum DownloadSide {
        ERP("ERP", 1),//
        TPL("TPL", 2),//
        WMS("WMS", 3);

        private final String name;

        private final int ordinal;

        DownloadSide(String name, int ordinal) {
            this.name = name;
            this.ordinal = ordinal;
        }
    }

    enum Priority {
        GREEN_CHANNEL("10", 10),//绿色通道
        APPEND_PICKING("15", 15),//追加拣货
        SCANGUN_PROMOTION("19", 19),//条码枪提升
        SELF_SERVICE("20", 20),//自提
        CURRENT_AREA_PROMOTION("21", 21),//本区提升
        PREVIOUS_AREA_PROMOTION("22", 22),//前区提升
        PURCHASE_RETURN("28", 28),//购进退出
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
        DEVICE_MANUAL_OPERATION("90", 90),//设备手动
        INBOUND_PURCHASE("95", 95);//购进入库

        private final String name;

        private final int ordinal;

        Priority(String name, int ordinal) {
            this.name = name;
            this.ordinal = ordinal;
        }
    }

    enum SaleType {
        NORMAL_SALE("1", 1),//正常销售
        FLITTING("2", 2),//调拨
        RETAIL_CHAINS("3", 3),//连锁
        PURCHASE_RETURN("4", 4),//购退
        STOCK_SORTINGOUT("5", 5),//库存整理
        EMERGENCY_OUTBOUND("6", 6),//紧急出库
        DECOCTION_BILL("7", 7),//煎药订单
        HOSPITAL_DELIVERY("8", 8),//医院配送
        CONSIGNMENT_BILL("9", 9);//寄售订单

        private final String name;

        private final int ordinal;

        SaleType(String name, int ordinal) {
            this.name = name;
            this.ordinal = ordinal;
        }
    }

    enum Stage {
        ORDER_GENERATED("00", 0),
        INIT_COMPLETE("C1", 11),
        WAVE_ARRANGED("D1", 21),
        WAVE_RELEASED("D2", 22),
        STAGINGAREA_ALLOCATION_COMPLETE("F1", 31),
        STOCKLESSNESS_SUSPENDED("F2", 32),
        STOCKLESSNESS_RELEASED("F3", 33),
        LOT_ALLOCATION_COMPLETE("F4", 34),
        TASKS_BALING_COMPLETE("F5", 35),
        SPLITING_COMPLETE("F6", 36),
        OPERATION_AVAILABLE("J1", 41),
        ON_WORKING("J2", 42),
        INNER_RECHECK_COMPLETE("N3", 51),
        ON_OUTER_RECHECKING("W1", 61),
        OUTER_RECHECK_COMPLETE("W2", 62),
        OPERATION_COMPLETE("ZZ", 99);

        private final String name;

        private final int ordinal;

        Stage(String name, int ordinal) {
            this.name = name;
            this.ordinal = ordinal;
        }
    }

    enum TakeMode {
        NONE("0", 0),
        SELF_SERVICE("1", 1),
        SELF_SERVICE_STOCKUP("2", 2),
        CONSIGNMENT("3", 3),
        INNER_CITY_DELIVERY("4", 4),
        OUTER_CITY_DELIVERY("5", 5),
        FLITTING("6", 6),
        GREEN_CHANNEL("7", 7),
        SELF_SERVICE_2_DELIVERY("8", 8),
        RETAIL_CHAINS("9", 9);

        private final String name;

        private final int ordinal;

        TakeMode(String name, int ordinal) {
            this.name = name;
            this.ordinal = ordinal;
        }
    }

    enum TaxTicketType {
        ;

        private final String name;

        private final int ordinal;

        TaxTicketType(String name, int ordinal) {
            this.name = name;
            this.ordinal = ordinal;
        }
    }

    enum Type {
        INBOUND("IB", 1),
        OUTBOUND("OB", 2),
        PURCHASE_RETURN("PR", 3),
        SALE_RETURN("SR", 4),
        INITIATIVE_REPLENISHMENT("IR", 5),
        EMERGENCY_REPLENISHMENT("ER", 6),
        LOCATION_MOVE("LM", 7),
        LOCATION_ADJUST("LA", 8);

        private final String name;

        private final int ordinal;

        Type(String name, int ordinal) {
            this.name = name;
            this.ordinal = ordinal;
        }
    }
}