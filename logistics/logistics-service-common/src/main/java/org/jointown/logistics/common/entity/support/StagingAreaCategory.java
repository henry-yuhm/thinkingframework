package org.jointown.logistics.common.entity.support;

public enum StagingAreaCategory {
    TINY_CUSTOMER("1", 1),//小型客户区
    MEDIUM_CUSTOMER("2", 2),//中等客户区
    HEAVY_CUSTOMER("3", 3);//大型客户区

    StagingAreaCategory(String name, int ordinal) {
    }
}