package org.thinking.logistics.services.core.domain.support;

//指令类型
public enum CommandType {
    PURCHASE_INBOUND(1) {
        @Override
        public String toString() {
            return "采购入库";
        }
    },
    SALE_OUTBOUND(2) {
        @Override
        public String toString() {
            return "销售出库";
        }
    },
    PURCHASE_RETURN(3) {
        @Override
        public String toString() {
            return "采购退货";
        }
    },
    SALE_RETURN(4) {
        @Override
        public String toString() {
            return "销售退回";
        }
    },
    REPLENISHMENT(5) {
        @Override
        public String toString() {
            return "补货";
        }
    },
    DEMONSTRATION(6) {
        @Override
        public String toString() {
            return "演示作业";
        }
    },
    MOVE(7) {
        @Override
        public String toString() {
            return "移库";
        }
    },
    PALLET_RETURN(8) {
        @Override
        public String toString() {
            return "回盘";
        }
    },
    INVENTORY_CHECK(9) {
        @Override
        public String toString() {
            return "盘点";
        }
    },
    GIFT_OUTBOUND(10) {
        @Override
        public String toString() {
            return "赠品出库";
        }
    },
    LOT_ADJUST(11) {
        @Override
        public String toString() {
            return "批号调整";
        }
    };

    CommandType(int ordinal) {
    }
}