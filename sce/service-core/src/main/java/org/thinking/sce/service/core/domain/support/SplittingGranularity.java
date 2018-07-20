package org.thinking.sce.service.core.domain.support;

//拆分粒度
public enum SplittingGranularity {
    REMAINDER(1) {
        @Override
        public String toString() {
            return "可以拆零";
        }
    },
    MEDIUM_PACKAGE(2) {
        @Override
        public String toString() {
            return "不拆中包装";
        }
    },
    WHOLE_PACKAGE(3) {
        @Override
        public String toString() {
            return "不拆大包装";
        }
    },
    DECIMAL(4) {
        @Override
        public String toString() {
            return "可以小数";
        }
    };

    SplittingGranularity(int ordinal) {
    }
}