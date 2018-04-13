package org.thinking.logistics.services.core.domain.support;

//复核台类型
public enum RecheckTableType {
    NORMAL("1", 1),//正常
    CULLING("2", 2);//剔除

    RecheckTableType(String name, int ordinal) {
    }
}