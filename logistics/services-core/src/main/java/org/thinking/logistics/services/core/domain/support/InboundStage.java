package org.thinking.logistics.services.core.domain.support;

//入库阶段
public enum InboundStage {
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
    COMPLETE(2) {
        @Override
        public String toString() {
            return "作业完成";
        }
    };

    InboundStage(int ordinal) {
    }
}