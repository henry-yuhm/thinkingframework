package org.thinking.logistics.services.core.domain.support;

//记账类别
public enum ChargingCategory {
    CHARGING(0) {
        @Override
        public String toString() {
            return "记账";
        }
    },
    INBOUND(1) {
        @Override
        public String toString() {
            return "入库预占";
        }
    },
    OUTBOUND(2) {
        @Override
        public String toString() {
            return "出库预扣";
        }
    },
    REPLENISH_FROM(3) {
        @Override
        public String toString() {
            return "补货预占";
        }
    },
    REPLENISH_TO(4) {
        @Override
        public String toString() {
            return "补货预扣";
        }
    },
    IN_TRANSITION(5) {
        @Override
        public String toString() {
            return "在途";
        }
    },
    LOCKING(6) {
        @Override
        public String toString() {
            return "锁定";
        }
    },
    UNLOCKING(7) {
        @Override
        public String toString() {
            return "解锁";
        }
    };

    ChargingCategory(int ordinal) {
    }
}