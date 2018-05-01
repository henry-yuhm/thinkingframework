package org.thinking.logistics.services.core.domain.support;

//货架类型
public enum RackType {
    CLAPBOARD(1) {
        @Override
        public String toString() {
            return "隔板式";
        }
    },
    FLUENCY(2) {
        @Override
        public String toString() {
            return "流利式";
        }
    };

    RackType(int ordinal) {
    }
}