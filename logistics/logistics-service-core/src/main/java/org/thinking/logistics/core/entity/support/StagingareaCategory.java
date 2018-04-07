package org.thinking.logistics.core.entity.support;

public enum StagingareaCategory {
    TINY_CUSTOMER("1", 1),//小型客户区
    MEDIUM_CUSTOMER("2", 2),//中等客户区
    HEAVY_CUSTOMER("3", 3);//大型客户区

    StagingareaCategory(String name, int ordinal) {
    }
}