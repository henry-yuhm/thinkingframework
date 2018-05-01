package org.thinking.logistics.services.core.domain.support;

//月台类型
public enum StagingareaType {
    NORMAL(1) {
        @Override
        public String toString() {
            return "普通";
        }
    },
    APPENDANT_PICKING(2) {
        @Override
        public String toString() {
            return "追加拣货";
        }
    },
    TRADITIONAL_CHINESE_MEDICINE(3) {
        @Override
        public String toString() {
            return "中药";
        }
    },
    FAMILY_PLANNING_AND_APPLIANCE(4) {
        @Override
        public String toString() {
            return "计生器械";
        }
    },
    VIRTUAL(5) {
        @Override
        public String toString() {
            return "虚拟";
        }
    },
    RANDOM(6) {
        @Override
        public String toString() {
            return "机动";
        }
    };

    StagingareaType(int ordinal) {
    }
}