package org.thinking.logistics.services.core.domain.support;

//出库优先级
public enum OutboundPriority {
    GREEN_CHANNEL(10) {
        @Override
        public String toString() {
            return "绿色通道";
        }
    },
    APPENDANT_PICKING(15) {
        @Override
        public String toString() {
            return "追加拣货";
        }
    },
    SCANGUN_PROMOTION(19) {
        @Override
        public String toString() {
            return "条码枪提升";
        }
    },
    SELF_SERVICE(20) {
        @Override
        public String toString() {
            return "自提";
        }
    },
    CURRENT_AREA_PROMOTION(21) {
        @Override
        public String toString() {
            return "本区提升";
        }
    },
    PREVIOUS_AREA_PROMOTION(22) {
        @Override
        public String toString() {
            return "前区提升";
        }
    },
    PURCHASE_RETURN(28) {
        @Override
        public String toString() {
            return "购进退出";
        }
    },
    SELF_SERVICE_2_DISTRIBUTION(30) {
        @Override
        public String toString() {
            return "自提转配送";
        }
    },
    PASSIVITY_REPLENISHING(35) {
        @Override
        public String toString() {
            return "被动补货";
        }
    },
    CONSIGNMENT(40) {
        @Override
        public String toString() {
            return "托运";
        }
    },
    URBAN_DISTRIBUTION(50) {
        @Override
        public String toString() {
            return "市内配送";
        }
    },
    SELF_SERVICE_STOCKUP(53) {
        @Override
        public String toString() {
            return "自提备货";
        }
    },
    SUBURBAN_DISTRIBUTION(55) {
        @Override
        public String toString() {
            return "市外配送";
        }
    },
    RETAIL_CHAINS(60) {
        @Override
        public String toString() {
            return "连锁";
        }
    },
    INITIATIVE_REPLENISHING(65) {
        @Override
        public String toString() {
            return "主动补货";
        }
    },
    FLITTING_OUTBOUND(70) {
        @Override
        public String toString() {
            return "调拨出库";
        }
    },
    DEMONSTRATION_OUTBOUND(80) {
        @Override
        public String toString() {
            return "演示出库";
        }
    },
    DEVICE_MANUAL(90) {
        @Override
        public String toString() {
            return "设备手动";
        }
    };

    OutboundPriority(int ordinal) {
    }
}