package org.thinking.logistics.core.domain.support;

//库存状态
public enum InventoryState {
    QUALIFICATION("1", 1),//合格
    DISQUALIFICATION("2", 2),//不合格
    INBOUND("3", 3),//入库待验
    SALE_HALT("4", 4),//停售
    VIRTUAL("5", 5),//虚拟库存
    IN_STOCK("6", 6);//在库待验

    InventoryState(String name, int ordinal) {
    }
}