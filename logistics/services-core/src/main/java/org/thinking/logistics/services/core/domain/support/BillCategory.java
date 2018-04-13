package org.thinking.logistics.services.core.domain.support;

//单据类别
public enum BillCategory {
    WESTERN_MEDICINE("1", 1),//西药
    TRADITIONAL_CHINESE_MEDICINE("2", 2),//中药
    FAMILY_PLANNING("3", 3),//计生
    APPLIANCE("4", 4),//器械
    RAW_MATERIAL("5", 5),//原料药
    GIFT("6", 6);//赠品

    BillCategory(String name, int ordinal) {
    }
}