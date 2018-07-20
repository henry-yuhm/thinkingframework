package org.thinking.sce.service.core.domain.support;

//效期类型
public enum ValidPeriodType {
    ALL(0) {
        @Override
        public String toString() {
            return "全部";
        }
    },
    OLD(1) {
        @Override
        public String toString() {
            return "老批号";
        }
    },
    NEW(2) {
        @Override
        public String toString() {
            return "新批号";
        }
    };

    ValidPeriodType(int ordinal) {
    }
}