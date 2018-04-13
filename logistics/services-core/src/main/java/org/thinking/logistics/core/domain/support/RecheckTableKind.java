package org.thinking.logistics.core.domain.support;

//复核台类型
public enum RecheckTableKind {
    NORMAL("1", 1),//正常
    CULLING("2", 2);//剔除

    RecheckTableKind(String name, int ordinal) {
    }
}