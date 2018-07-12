package org.thinking.logistics.services.core.domain.support;

//发运状态
public enum ShipmentStatus {
    CREATED(0) {
        @Override
        public String toString() {
            return "单据生成";
        }
    },
    INITIALIZED(11) {
        @Override
        public String toString() {
            return "初始化完成";
        }
    },
    ARRANGED(21) {
        @Override
        public String toString() {
            return "波次已安排";
        }
    },
    RELEASED(22) {
        @Override
        public String toString() {
            return "已下发";
        }
    },
    STAGINGAREA_ALLOCATED(31) {
        @Override
        public String toString() {
            return "月台已分配";
        }
    },
    SUSPENDED(32) {
        @Override
        public String toString() {
            return "单据挂起";
        }
    },
    RESEND(33) {
        @Override
        public String toString() {
            return "单据补发";
        }
    },
    LOT_ALLOCATED(34) {
        @Override
        public String toString() {
            return "批号已分配";
        }
    },
    PACK_COMPLETE(35) {
        @Override
        public String toString() {
            return "打包完成";
        }
    },
    SPLIT_COMPLETE(36) {
        @Override
        public String toString() {
            return "拆分完成";
        }
    },
    TASK_READY(41) {
        @Override
        public String toString() {
            return "任务准备";
        }
    },
    IN_WORKING(42) {
        @Override
        public String toString() {
            return "正在作业";
        }
    },
    TASK_COMPLETE(51) {
        @Override
        public String toString() {
            return "任务完成";
        }
    },
    IN_REVIEWING(61) {
        @Override
        public String toString() {
            return "正在外复核";
        }
    },
    REVIEWED_COMPLETE(62) {
        @Override
        public String toString() {
            return "外复核完成";
        }
    },
    TERMINATED(99) {
        @Override
        public String toString() {
            return "作业终止";
        }
    };

    ShipmentStatus(int ordinal) {
    }
}