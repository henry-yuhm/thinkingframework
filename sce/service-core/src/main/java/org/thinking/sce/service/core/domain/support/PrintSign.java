package org.thinking.sce.service.core.domain.support;

//打印标识
public enum PrintSign {
    NONE(0) {
        @Override
        public String toString() {
            return "未打印";
        }
    },
    DONE(1) {
        @Override
        public String toString() {
            return "已打印";
        }
    },
    CONFIRMATION(2) {
        @Override
        public String toString() {
            return "打印确认";
        }
    };

    PrintSign(int ordinal) {
    }
}