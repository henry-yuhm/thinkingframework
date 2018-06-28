package org.thinking.logistics.services.core.domain.support;

// 冲红阶段
public enum ReversionStage {
    DISPATCHING(1) {
        @Override
        public String toString() {
            return "出库调度";
        }
    },
    EXECUTING(2) {
        @Override
        public String toString() {
            return "购退执行";
        }
    },
    SUSPENDING(3) {
        @Override
        public String toString() {
            return "单据挂起";
        }
    },
    PICKING(4) {
        @Override
        public String toString() {
            return "拣货";
        }
    },
    RECHECKING(5) {
        @Override
        public String toString() {
            return "复核";
        }
    };

    ReversionStage(int ordinal) {
    }
}