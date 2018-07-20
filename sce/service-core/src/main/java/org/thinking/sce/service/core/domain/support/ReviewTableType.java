package org.thinking.sce.service.core.domain.support;

//复核台类型
public enum ReviewTableType {
    NORMAL(1) {
        @Override
        public String toString() {
            return "正常";
        }
    },
    CULLING(2) {
        @Override
        public String toString() {
            return "剔除";
        }
    };

    ReviewTableType(int ordinal) {
    }
}