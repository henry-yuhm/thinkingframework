package org.jointown.logistics.core.entity.support;

public enum SaleType {
    NORMAL_SALE("1", 1),//正常销售
    FLITTING("2", 2),//调拨
    RETAIL_CHAINS("3", 3),//连锁
    RETURNED_PURCHASE("4", 4),//购进退出
    STOCK_SORTINGOUT("5", 5),//库存整理
    OUTBOUND_EMERGENCY("6", 6),//紧急出库
    DECOCTION_BILL("7", 7),//煎药订单
    HOSPITAL_DELIVERY("8", 8),//医院配送
    CONSIGNMENT_BILL("9", 9);//寄售订单

    SaleType(String name, int ordinal) {
    }
}