package org.thinking.logistics.services.core.domain.support;

//收货状态
public enum ReceivingStatus {
    CREATED(0) {
        @Override
        public String toString() {
            return "刚生成";
        }
    },
    WORKING(1) {
        @Override
        public String toString() {
            return "正在作业";
        }
    },
    COMPLETED(2) {
        @Override
        public String toString() {
            return "作业完成";
        }
    };

    ReceivingStatus(int ordinal) {
    }
}