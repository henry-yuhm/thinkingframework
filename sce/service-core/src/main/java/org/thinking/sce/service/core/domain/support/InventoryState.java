package org.thinking.sce.service.core.domain.support;

//库存状态
public enum InventoryState {
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
    SALE_HALT(4) {
        @Override
        public String toString() {
            return "停售";
        }
    },
    VIRTUAL(5) {
        @Override
        public String toString() {
            return "虚拟库存";
        }
    },
    INSTOCK(6) {
        @Override
        public String toString() {
            return "在库待验";
        }
    };

    InventoryState(int ordinal) {
    }
}