package org.thinking.sce.service.core.domain.support;

//入库类型
public enum InboundType {
    NORMAL(1) {
        @Override
        public String toString() {
            return "正常入库";
        }
    },
    THROUGH(2) {
        @Override
        public String toString() {
            return "直通入库";
        }
    },
    PURCHASE_RETURN(3) {
        @Override
        public String toString() {
            return "购退入库";
        }
    },
    SALE_RETURN(4) {
        @Override
        public String toString() {
            return "销退入库";
        }
    },
    GIFT(5) {
        @Override
        public String toString() {
            return "赠品入库";
        }
    };

    InboundType(int ordinal) {
    }
}