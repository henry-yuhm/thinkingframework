package org.thinking.sce.service.core.domain.support;

//度量单位
public enum MeasureUnit {
    EACH(1) {
        @Override
        public String toString() {
            return "EA";
        }
    },
    INNERPACK(2) {
        @Override
        public String toString() {
            return "内包装";
        }
    },
    CASE(3) {
        @Override
        public String toString() {
            return "箱";
        }
    },
    PALLET(4) {
        @Override
        public String toString() {
            return "托盘";
        }
    },
    CUBE(5) {
        @Override
        public String toString() {
            return "体积";
        }
    },
    GROSS_WEIGHT(6) {
        @Override
        public String toString() {
            return "毛重";
        }
    },
    NET_WEIGHT(7) {
        @Override
        public String toString() {
            return "净重";
        }
    },
    LAYER(8) {
        @Override
        public String toString() {
            return "层次";
        }
    },
    FEET(9) {
        @Override
        public String toString() {
            return "英尺";
        }
    };

    MeasureUnit(int ordinal) {
    }
}