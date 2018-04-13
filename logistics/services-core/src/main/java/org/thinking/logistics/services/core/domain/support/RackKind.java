package org.thinking.logistics.services.core.domain.support;

//货架类型
public enum RackKind {
    CLAPBOARD("1", 1),//隔板式
    FLUENCY("2", 2);//流利式

    RackKind(String name, int ordinal) {
    }
}