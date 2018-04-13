package org.thinking.logistics.services.core.domain.support;

//码放类型
public enum PileupType {
    STACK("1", 1),//堆垛
    RACK("2", 2);//货架

    PileupType(String name, int ordinal) {
    }
}