package org.thinking.sce.service.core.domain.support;

//追加标识
public enum AppendantSign {
    ORIGINAL(1) {
        @Override
        public String toString() {
            return "原始指令";
        }
    },
    APPENDING(2) {
        @Override
        public String toString() {
            return "正在追加";
        }
    },
    APPEND(3) {
        @Override
        public String toString() {
            return "追加指令";
        }
    },
    DISAPPEND(4) {
        @Override
        public String toString() {
            return "不追加";
        }
    };

    AppendantSign(int ordinal) {
    }
}