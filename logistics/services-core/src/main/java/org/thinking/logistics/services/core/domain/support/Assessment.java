package org.thinking.logistics.services.core.domain.support;

//验收评定
public enum Assessment {
    QUALIFICATION(1) {
        @Override
        public String toString() {
            return "合格";
        }
    },
    DISQUALIFICATION(2) {
        @Override
        public String toString() {
            return "不合格";
        }
    },
    INBOUND(3) {
        @Override
        public String toString() {
            return "入库待验";
        }
    },
    REJECTION(4) {
        @Override
        public String toString() {
            return "拒收";
        }
    },
    PENDING_DETERMINATION(5) {
        @Override
        public String toString() {
            return "待验确定";
        }
    },
    INSTOCK(6) {
        @Override
        public String toString() {
            return "在库待验";
        }
    };

    Assessment(int ordinal) {
    }
}