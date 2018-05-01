package org.thinking.logistics.services.core.domain.support;

//复核台类别
public enum RecheckTableCategory {
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
    THIRD_PARTY(6) {
        @Override
        public String toString() {
            return "第三方";
        }
    },
    GIFT(7) {
        @Override
        public String toString() {
            return "赠品";
        }
    };

    RecheckTableCategory(int ordinal) {
    }
}