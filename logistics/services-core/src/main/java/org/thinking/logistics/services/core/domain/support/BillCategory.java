package org.thinking.logistics.services.core.domain.support;

//单据类别
public enum BillCategory {
    WESTERN_MEDICINE(1) {
        @Override
        public String toString() {
            return "西药";
        }
    },
    TRADITIONAL_CHINESE_MEDICINE(2) {
        @Override
        public String toString() {
            return "中药";
        }
    },
    FAMILY_PLANNING(3) {
        @Override
        public String toString() {
            return "计生";
        }
    },
    APPLIANCE(4) {
        @Override
        public String toString() {
            return "器械";
        }
    },
    RAW_MATERIAL(5) {
        @Override
        public String toString() {
            return "原料药";
        }
    },
    GIFT(6) {
        @Override
        public String toString() {
            return "赠品";
        }
    };

    BillCategory(int ordinal) {
    }
}