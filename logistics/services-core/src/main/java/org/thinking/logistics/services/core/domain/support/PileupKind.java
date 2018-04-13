package org.thinking.logistics.services.core.domain.support;

//码放类型
public enum PileupKind {
    STACK("1", 1),//堆垛
    RACK("2", 2);//货架

    PileupKind(String name, int ordinal) {
    }
}