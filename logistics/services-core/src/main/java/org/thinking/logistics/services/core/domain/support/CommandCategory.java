package org.thinking.logistics.services.core.domain.support;

//指令类别
public enum CommandCategory {
    INVENTORY_CHECK(1) {
        @Override
        public String toString() {
            return "盘点";
        }
    },
    GREEN_CHANNEL(2) {
        @Override
        public String toString() {
            return "绿色通道";
        }
    },
    PURCHASE_RETURN(3) {
        @Override
        public String toString() {
            return "购进退出";
        }
    },
    SELF_SERVICE_OUTBOUND(4) {
        @Override
        public String toString() {
            return "自提出库";
        }
    },
    NORMAL_OUTBOUND(5) {
        @Override
        public String toString() {
            return "普通出库";
        }
    },
    PASSIVITY_REPLENISHMENT(6) {
        @Override
        public String toString() {
            return "被动补货";
        }
    },
    INITIATIVE_REPLENISHMENT(7) {
        @Override
        public String toString() {
            return "主动补货";
        }
    },
    UPSHELF(9) {
        @Override
        public String toString() {
            return "入库上架";
        }
    },
    TRANSFERRING(10) {
        @Override
        public String toString() {
            return "移库";
        }
    },
    GIFT_OUTBOUND(11) {
        @Override
        public String toString() {
            return "赠品出库";
        }
    },
    LOT_ADJUST(12) {
        @Override
        public String toString() {
            return "批号调整";
        }
    };

    CommandCategory(int ordinal) {
    }
}