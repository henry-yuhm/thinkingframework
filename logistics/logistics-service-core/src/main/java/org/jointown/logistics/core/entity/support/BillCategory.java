package org.jointown.logistics.core.entity.support;

public enum BillCategory {
    WESTERN_MEDICINE("1", 1),//西药
    TRADITIONAL_CHINESE_MEDICINE("2", 2),//中药
    FAMILY_PLANNING("3", 3),//计生
    APPLIANCE("4", 4),//器械
    RAW_MATERIAL("5", 5),//原料药
    THIRD_PARTY("6", 6),//第三方
    GIFT("7", 7);//赠品

    BillCategory(String name, int ordinal) {
    }
}