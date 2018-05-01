package org.thinking.logistics.services.core.domain.support;

//提货方式
public enum TakegoodsMode {
    NONE(0) {
        @Override
        public String toString() {
            return "无";
        }
    },
    SELF_SERVICE(1) {
        @Override
        public String toString() {
            return "自提";
        }
    },
    SELF_SERVICE_STOCKUP(2) {
        @Override
        public String toString() {
            return "自提备货";
        }
    },
    CONSIGNMENT(3) {
        @Override
        public String toString() {
            return "托运";
        }
    },
    URBAN_DISTRIBUTION(4) {
        @Override
        public String toString() {
            return "市内配送";
        }
    },
    SUBURBAN_DISTRIBUTION(5) {
        @Override
        public String toString() {
            return "市外配送";
        }
    },
    FLITTING(6) {
        @Override
        public String toString() {
            return "调拨";
        }
    },
    GREEN_CHANNEL(7) {
        @Override
        public String toString() {
            return "绿色通道";
        }
    },
    SELF_SERVICE_2_DISTRIBUTION(8) {
        @Override
        public String toString() {
            return "自提转配送";
        }
    },
    RETAIL_CHAINS(9) {
        @Override
        public String toString() {
            return "连锁";
        }
    };

    TakegoodsMode(int ordinal) {
    }
}