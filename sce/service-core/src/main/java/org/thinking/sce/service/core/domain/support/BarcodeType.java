package org.thinking.sce.service.core.domain.support;

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
    REPLENISHMENT(3) {
        @Override
        public String toString() {
            return "补货";
        }
    },
    TRANSFER(4) {
        @Override
        public String toString() {
            return "移库";
        }
    };

    BarcodeType(int ordinal) {
    }
}