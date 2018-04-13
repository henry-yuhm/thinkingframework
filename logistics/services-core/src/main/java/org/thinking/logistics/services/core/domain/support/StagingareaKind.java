package org.thinking.logistics.services.core.domain.support;

//月台类型
public enum StagingareaKind {
    NORMAL("1", 1),//普通
    //    APPENDANT_PICKING("2", 2),//追加拣货
//    TRADITIONAL_CHINESE_MEDICINE("3", 3),//中药
//    FAMILY_PLANNING_AND_APPLIANCE("4", 4),//计生器械
    VIRTUAL("5", 5),//虚拟
    RANDOM("6", 6);//机动

    StagingareaKind(String name, int ordinal) {
    }
}