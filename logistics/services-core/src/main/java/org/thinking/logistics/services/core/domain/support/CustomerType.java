package org.thinking.logistics.services.core.domain.support;

//客户类型
public enum CustomerType {
    PURCHASE(1) {
        @Override
        public String toString() {
            return "采购客户";
        }
    },
    SALE(2) {
        @Override
        public String toString() {
            return "销售客户";
        }
    };

    CustomerType(int ordinal) {
    }
}