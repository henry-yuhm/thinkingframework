package org.jointown.logistics.core.entity.support;

public enum StockStatus {
    QUALIFICATION("1", 1),//合格
    DISQUALIFICATION("2", 2),//不合格
    INBOUND_CHECKING("3", 3),//入库待验
    SALE_CESSATION("4", 4),//停售
    VIRTUAL("5", 5),//虚拟库存
    INSTORE_CHECKING("6", 6);//在库待验

    StockStatus(String name, int ordinal) {
    }
}