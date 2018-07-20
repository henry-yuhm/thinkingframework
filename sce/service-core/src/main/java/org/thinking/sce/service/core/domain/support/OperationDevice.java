package org.thinking.sce.service.core.domain.support;

//拣货设备
public enum OperationDevice {
    PC(1) {
        @Override
        public String toString() {
            return "个人电脑";
        }
    },
    PDA(2) {
        @Override
        public String toString() {
            return "PDA";
        }
    },
    DPS(3) {
        @Override
        public String toString() {
            return "电子标签";
        }
    },
    TPC(4) {
        @Override
        public String toString() {
            return "平板电脑";
        }
    };

    OperationDevice(int ordinal) {
    }
}