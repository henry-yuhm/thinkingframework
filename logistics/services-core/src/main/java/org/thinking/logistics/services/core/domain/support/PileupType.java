package org.thinking.logistics.services.core.domain.support;

//码放类型
public enum PileupType {
    STACK(1) {
        @Override
        public String toString() {
            return "堆垛";
        }
    },
    RACK(2) {
        @Override
        public String toString() {
            return "货架";
        }
    };

    PileupType(int ordinal) {
    }
}