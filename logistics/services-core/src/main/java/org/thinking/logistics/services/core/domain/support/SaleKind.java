package org.thinking.logistics.services.core.domain.support;

//销售类型
public enum SaleKind {
    NORMAL_SALE("1", 1),//正常销售
    FLITTING("2", 2),//调拨
    RETAIL_CHAINS("3", 3),//连锁
    PURCHASE_RETURN("4", 4),//购进退出
    INVENTORY_SORTINGOUT("5", 5),//库存整理
    EMERGENCY_OUTBOUND("6", 6),//紧急出库
    DECOCTION_OUTBOUND("7", 7),//煎药出库
    HOSPITAL_DISTRIBUTION("8", 8),//医院配送
    CONSIGNMENT_SALES("9", 9);//寄售

    SaleKind(String name, int ordinal) {
    }
}