package org.thinking.logistics.services.core.domain.support;

//单据来源
public enum BillSource {
    ERP(1) {
        @Override
        public String toString() {
            return "ERP";
        }
    },
    WMS(2) {
        @Override
        public String toString() {
            return "WMS";
        }
    },
    TPL(3) {
        @Override
        public String toString() {
            return "TPL";
        }
    },
    TMS(4) {
        @Override
        public String toString() {
            return "TMS";
        }
    };

    BillSource(int ordinal) {
    }
}