package org.thinking.sce.service.core.domain.support;

//复核类型
public enum ReviewType {
    MECHANICAL(1) {
        @Override
        public String toString() {
            return "设备";
        }
    },
    MANUAL(2) {
        @Override
        public String toString() {
            return "人工";
        }
    },
    VIRTUALIZATION(3) {
        @Override
        public String toString() {
            return "虚拟";
        }
    },
    NONE(4) {
        @Override
        public String toString() {
            return "无";
        }
    };

    ReviewType(int ordinal) {
    }
}