package org.thinking.sce.service.core.domain.support;

//作业方式
public enum WorkMode {
    LABEL(1) {
        @Override
        public String toString() {
            return "标签";
        }
    },
    PAPER(2) {
        @Override
        public String toString() {
            return "纸单";
        }
    },
    PDA(3) {
        @Override
        public String toString() {
            return "PDA";
        }
    },
    DPS(4) {
        @Override
        public String toString() {
            return "电子标签";
        }
    },
    TPC(5) {
        @Override
        public String toString() {
            return "平板";
        }
    },
    PILER(6) {
        @Override
        public String toString() {
            return "堆垛机";
        }
    };

    WorkMode(int ordinal) {
    }
}