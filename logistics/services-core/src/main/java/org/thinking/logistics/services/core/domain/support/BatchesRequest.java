package org.thinking.logistics.services.core.domain.support;

//批号要求
public enum BatchesRequest {
    SINGLE(1) {
        @Override
        public String toString() {
            return "单一批号";
        }
    },
    NEW(2) {
        @Override
        public String toString() {
            return "新批号";
        }
    },
    SINGLE_NEW(3) {
        @Override
        public String toString() {
            return "单一新批号";
        }
    },
    NO_DEMAND(4) {
        @Override
        public String toString() {
            return "无要求";
        }
    },
    CLEANUP(5) {
        @Override
        public String toString() {
            return "清理批号";
        }
    };

    BatchesRequest(int ordinal) {
    }
}