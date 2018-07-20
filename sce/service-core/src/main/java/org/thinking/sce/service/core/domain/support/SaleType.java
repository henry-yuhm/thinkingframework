package org.thinking.sce.service.core.domain.support;

//销售类型
public enum SaleType {
    NORMAL_SALE(1) {
        @Override
        public String toString() {
            return "正常销售";
        }
    },
    FLITTING(2) {
        @Override
        public String toString() {
            return "调拨";
        }
    },
    RETAIL_CHAINS(3) {
        @Override
        public String toString() {
            return "连锁";
        }
    },
    PURCHASE_RETURN(4) {
        @Override
        public String toString() {
            return "购进退出";
        }
    },
    INVENTORY_SORTINGOUT(5) {
        @Override
        public String toString() {
            return "库存整理";
        }
    },
    EMERGENCY_OUTBOUND(6) {
        @Override
        public String toString() {
            return "紧急出库";
        }
    },
    DECOCTION_OUTBOUND(7) {
        @Override
        public String toString() {
            return "煎药出库";
        }
    },
    HOSPITAL_DISTRIBUTION(8) {
        @Override
        public String toString() {
            return "医院配送";
        }
    },
    CONSIGNMENT_SALES(9) {
        @Override
        public String toString() {
            return "骨科寄售";
        }
    };

    SaleType(int ordinal) {
    }
}