package org.thinking.logistics.services.core.domain.support;

//批号要求
public enum BatchesRequest {
    SINGLE("1", 1),//单一批号
    NEW("2", 2),//新批号
    SINGLE_NEW("3", 3),//单一新批号
    NO_DEMAND("4", 4),//无要求
    CLEANUP("5", 5);//清理批号

    BatchesRequest(String name, int ordinal) {
    }
}