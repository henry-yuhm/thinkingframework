package org.thinking.logistics.services.core.domain.support;

//数据来源
public enum DataSource {
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

    DataSource(int ordinal) {
    }
}