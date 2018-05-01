package org.thinking.logistics.services.core.domain.support;

//销售分类
public enum SaleClassification {
    A(1) {
        @Override
        public String toString() {
            return "A级";
        }
    },
    B(2) {
        @Override
        public String toString() {
            return "B级";
        }
    },
    C(3) {
        @Override
        public String toString() {
            return "C级";
        }
    };

    SaleClassification(int ordinal) {
    }
}