package org.thinking.logistics.core.domain.support;

public enum InboundStage {
    CREATED("0", 0),//刚生成
    WORKING("1", 1),//正在作业
    COMPLETE("2", 2);//作业完成

    InboundStage(String name, int ordinal) {
    }
}