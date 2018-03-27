package org.jointown.logistics.core.entity.support;

public enum BatchRequest {
    SINGLE("1", 1),//单一批号
    NEW("2", 2),//新批号
    SINGLE_NEW("3", 3),//单一新批号
    NONE("4", 4),//无要求
    CLEANUP("5", 5);//清理批号

    BatchRequest(String name, int ordinal) {
    }
}