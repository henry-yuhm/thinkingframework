package org.jointown.logistics.core.entity.support;

public enum PileupType {
    STACKING("1", 1),//堆垛
    RACK("2", 2);//货架

    PileupType(String name, int ordinal) {
    }
}