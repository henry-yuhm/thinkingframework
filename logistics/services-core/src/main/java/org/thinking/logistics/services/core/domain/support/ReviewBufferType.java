package org.thinking.logistics.services.core.domain.support;

//复核暂存位类型
public enum ReviewBufferType {
    NORMAL(1) {
        @Override
        public String toString() {
            return "普通";
        }
    },
    APPENDANT(2) {
        @Override
        public String toString() {
            return "追加";
        }
    };

    ReviewBufferType(int ordinal) {
    }
}