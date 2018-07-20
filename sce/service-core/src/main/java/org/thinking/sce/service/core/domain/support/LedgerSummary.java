package org.thinking.sce.service.core.domain.support;

//账页摘要
public enum LedgerSummary {
    OUTBOUND_RELEASING_PREALLOCATION(21) {
        @Override
        public String toString() {
            return "出库下发-预分配";
        }
    },
    OUTBOUND_MINUS_TRANSITION(22) {
        @Override
        public String toString() {
            return "出库在途-减在途";
        }
    },
    OUTBOUND_PLUS_TRANSITION(23) {
        @Override
        public String toString() {
            return "出库在途-加在途";
        }
    };

    LedgerSummary(int ordinal) {
    }
}