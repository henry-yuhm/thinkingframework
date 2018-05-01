package org.thinking.logistics.services.core.domain.support;

//记账类型
public enum ChargingType {
    PREALLOCATION(1) {
        @Override
        public String toString() {
            return "预分配";
        }
    },
    CHARGING(2) {
        @Override
        public String toString() {
            return "记账";
        }
    },
    IN_TRANSITION(3) {
        @Override
        public String toString() {
            return "在途";
        }
    },
    LOCKING(4) {
        @Override
        public String toString() {
            return "锁定";
        }
    };

    ChargingType(int ordinal) {
    }
}