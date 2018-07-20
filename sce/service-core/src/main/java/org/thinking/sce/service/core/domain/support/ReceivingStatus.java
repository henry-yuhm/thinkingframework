package org.thinking.sce.service.core.domain.support;

//收货状态
public enum ReceivingStatus {
    CREATED(0) {
        @Override
        public String toString() {
            return "刚生成";
        }
    },
    IN_RECEIVING(1) {
        @Override
        public String toString() {
            return "正在收货";
        }
    },
    RECEIVED_COMPLETE(2) {
        @Override
        public String toString() {
            return "收货完成";
        }
    };

    ReceivingStatus(int ordinal) {
    }
}