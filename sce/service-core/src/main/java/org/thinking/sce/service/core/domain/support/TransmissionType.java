package org.thinking.sce.service.core.domain.support;

//传输类型
public enum TransmissionType {
    NONE(0) {
        @Override
        public String toString() {
            return "无";
        }
    },
    TRANSFERLINE(1) {
        @Override
        public String toString() {
            return "输送线";
        }
    },
    ELEVATOR(2) {
        @Override
        public String toString() {
            return "电梯";
        }
    };

    TransmissionType(int ordinal) {
    }
}