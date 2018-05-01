package org.thinking.logistics.services.core.domain.support;

//单据类型
public enum BillType {
    PURCHASE_INBOUND(11) {
        @Override
        public String toString() {
            return "采购入库";
        }
    },
    SALE_OUTBOUND(12) {
        @Override
        public String toString() {
            return "销售出库";
        }
    },
    PURCHASE_RETURN(13) {
        @Override
        public String toString() {
            return "采购退货";
        }
    },
    SALE_RETURN(14) {
        @Override
        public String toString() {
            return "销售退回";
        }
    },
    INITIATIVE_REPLENISHING(21) {
        @Override
        public String toString() {
            return "主动补货";
        }
    },
    EMERGENCY_REPLENISHING(22) {
        @Override
        public String toString() {
            return "紧急补货";
        }
    },
    TRANSFERRING(31) {
        @Override
        public String toString() {
            return "移库";
        }
    },
    LOCATION_ADJUST(32) {
        @Override
        public String toString() {
            return "货位调整";
        }
    },
    INVENTORY_CHECK(41) {
        @Override
        public String toString() {
            return "盘点";
        }
    },
    DISTRIBUTION(51) {
        @Override
        public String toString() {
            return "配送";
        }
    };

    BillType(int ordinal) {
    }
}