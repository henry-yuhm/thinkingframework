package org.jointown.logistics.common.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class OutboundBillHeader extends BillHeader {
    private String waveNo;

    private String dispatchingType;

    private String dispatcher;

    private Date dispatchingTime;

    private TakeMode takeMode;

    private TakeMode takeModeConvertor;

    private Priority priority;

    private SaleType saleType;

    private String deliveryType;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_address"))
    private Address address;

    public enum TakeMode {
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

    public enum Priority {
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

    public enum SaleType {
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
}