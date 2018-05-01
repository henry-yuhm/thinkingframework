package org.thinking.logistics.services.core.domain.support;

//条码类型
public enum BarcodeType {
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
    REPLENISHING(3) {
        @Override
        public String toString() {
            return "补货";
        }
    },
    TRANSFERRING(4) {
        @Override
        public String toString() {
            return "移库";
        }
    };

    BarcodeType(int ordinal) {
    }
}