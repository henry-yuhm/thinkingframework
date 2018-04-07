package org.thinking.logistics.core.domain.support;

public enum StagingareaType {
    NORMAL("1", 1),//普通
    APPENDANT_PICKING("2", 2),//追加拣货
    TRADITIONAL_CHINESE_MEDICINE("3", 3),//中药
    FAMILY_PLANNING_AND_APPLIANCE("4", 4),//计生器械
    VIRTUAL("5", 5),//虚拟
    THIRD_PARTY("6", 6),//第三方
    RANDOM("7", 7);//机动

    StagingareaType(String name, int ordinal) {
    }
}