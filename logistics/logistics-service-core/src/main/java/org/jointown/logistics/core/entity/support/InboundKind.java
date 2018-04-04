package org.jointown.logistics.core.entity.support;

public enum InboundKind {
    NORMAL("1", 1),//正常入库
    THROUGH("2", 2),//直通入库
    RETURNED_PURCHASE("3", 3),//购退入库
    RETURNED_SALE("4", 4),//销退入库
    GIFT("5", 5);//赠品入库

    InboundKind(String name, int ordinal) {
    }
}