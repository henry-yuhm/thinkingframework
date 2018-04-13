package org.thinking.logistics.services.core.domain.support;

//货架类型
public enum RackType {
    CLAPBOARD("1", 1),//隔板式
    FLUENCY("2", 2);//流利式

    RackType(String name, int ordinal) {
    }
}