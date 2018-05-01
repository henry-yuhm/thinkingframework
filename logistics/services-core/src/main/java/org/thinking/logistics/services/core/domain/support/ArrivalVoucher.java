package org.thinking.logistics.services.core.domain.support;

//到货凭证
public enum ArrivalVoucher {
    HOLD(1) {
        @Override
        public String toString() {
            return "有";
        }
    },
    NONE(2) {
        @Override
        public String toString() {
            return "无";
        }
    },
    DISQUALIFICATION(3) {
        @Override
        public String toString() {
            return "不合格";
        }
    };

    ArrivalVoucher(int ordinal) {
    }
}