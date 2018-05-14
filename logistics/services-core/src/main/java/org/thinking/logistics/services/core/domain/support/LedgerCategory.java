package org.thinking.logistics.services.core.domain.support;

//账页类别
public enum LedgerCategory {
    INBOUND(1) {
        @Override
        public String toString() {
            return "入库";
        }
    },
    OUTBOUND(2) {
        @Override
        public String toString() {
            return "出库";
        }
    },
    REPLENISHED_FROM(3) {
        @Override
        public String toString() {
            return "补出";
        }
    },
    REPLENISHED_TO(4) {
        @Override
        public String toString() {
            return "补入";
        }
    },
    TRANSFERRED_FROM(5) {
        @Override
        public String toString() {
            return "移出";
        }
    },
    TRANSFERRED_TO(6) {
        @Override
        public String toString() {
            return "移入";
        }
    },
    IN_TRANSITION(7) {
        @Override
        public String toString() {
            return "在途";
        }
    },
    LOCKING(8) {
        @Override
        public String toString() {
            return "锁定";
        }
    },
    UNLOCKING(9) {
        @Override
        public String toString() {
            return "解锁";
        }
    };

    LedgerCategory(int ordinal) {
    }
}