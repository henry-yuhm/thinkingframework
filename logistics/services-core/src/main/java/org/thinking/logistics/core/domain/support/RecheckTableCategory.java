package org.thinking.logistics.core.domain.support;

//复核台类别
public enum RecheckTableCategory {
    WESTERN_MEDICINE("1", 1),//西药
    TRADITIONAL_CHINESE_MEDICINE("2", 2),//中药
    FAMILY_PLANNING("3", 3),//计生
    APPLIANCE("4", 4),//器械
    RAW_MATERIAL("5", 5),//原料药
    THIRD_PARTY("6", 6),//第三方
    GIFT("7", 7);//赠品

    RecheckTableCategory(String name, int ordinal) {
    }
}