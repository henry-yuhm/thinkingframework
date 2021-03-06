package org.thinking.sce.service.core.domain.support;

//周转方式
public enum TurnoverMode {
    FIFO(1) {
        @Override
        public String toString() {
            return "先进先出";
        }
    },
    LIFO(2) {
        @Override
        public String toString() {
            return "后进先出";
        }
    },
    FEFO(3) {
        @Override
        public String toString() {
            return "先到期先出";
        }
    },
    LOTTABLE(4) {
        @Override
        public String toString() {
            return "指定批次";
        }
    };

    TurnoverMode(int ordinal) {
    }
}