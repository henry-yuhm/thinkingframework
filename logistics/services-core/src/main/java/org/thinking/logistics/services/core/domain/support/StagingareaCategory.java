package org.thinking.logistics.services.core.domain.support;

//月台类别
public enum StagingareaCategory {
    MINIATURE("1", 1),//小型
    MEDIUM("2", 2),//中等
    HEAVY("3", 3);//大型

    StagingareaCategory(String name, int ordinal) {
    }
}