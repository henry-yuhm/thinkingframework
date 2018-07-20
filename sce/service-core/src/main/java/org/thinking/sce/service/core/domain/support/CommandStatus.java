package org.thinking.sce.service.core.domain.support;

//指令状态
public enum CommandStatus {
    CREATED(0) {
        @Override
        public String toString() {
            return "指令刚生成";
        }
    },
    TASK_READY(11) {
        @Override
        public String toString() {
            return "可以作业";
        }
    },
    REPLENISHMENT_REMOVAL_OBTAINED(21) {
        @Override
        public String toString() {
            return "补货下架索取";
        }
    },
    REPLENISHMENT_REMOVAL_CONFIRMED(22) {
        @Override
        public String toString() {
            return "补货下架确认";
        }
    },
    REPLENISHMENT_PUTAWAY_OBTAINED(23) {
        @Override
        public String toString() {
            return "补货上架索取";
        }
    },
    REPLENISHMENT_PUTAWAY_CONFIRMED(24) {
        @Override
        public String toString() {
            return "补货上架确认";
        }
    },
    TASKBILL_CREATED(31) {
        @Override
        public String toString() {
            return "生成分配单";
        }
    },
    WHOLEBILL_OBTAINED(32) {
        @Override
        public String toString() {
            return "整单索取";
        }
    },
    PICKING_OBTAINED(33) {
        @Override
        public String toString() {
            return "拣货索取";
        }
    },
    PICKING_CONFIRMED(34) {
        @Override
        public String toString() {
            return "拣货确认";
        }
    },
    INNERREVIEW_OBTAINED(35) {
        @Override
        public String toString() {
            return "内复核任务索取";
        }
    },
    INNERREVIEW_ITEM_OBTAINED(36) {
        @Override
        public String toString() {
            return "内复核商品索取";
        }
    },
    INNERREVIEW_CONFIRMED(37) {
        @Override
        public String toString() {
            return "内复核确认";
        }
    },
    OUTERREVIEW_OBTAINED(41) {
        @Override
        public String toString() {
            return "外复核索取";
        }
    },
    OUTERREVIEW_CONFIRMED(42) {
        @Override
        public String toString() {
            return "外复核确认";
        }
    },
    LOADING_OBTAINED(51) {
        @Override
        public String toString() {
            return "装车任务索取";
        }
    },
    LOADING_CONFIRMED(52) {
        @Override
        public String toString() {
            return "装车任务确认";
        }
    },
    LOADINGREVIEW_OBTAINED(53) {
        @Override
        public String toString() {
            return "装车复核索取";
        }
    },
    LOADINGREVIEW_CONFIRMED(54) {
        @Override
        public String toString() {
            return "装车复核确认";
        }
    },
    LOADING_INFORMATION_CONFIRMED(55) {
        @Override
        public String toString() {
            return "装车信息确认";
        }
    },
    DISTRIBUTION_BACK_CONFIRMED(56) {
        @Override
        public String toString() {
            return "配送返回确认";
        }
    },
    PALLET_OBTAINED(61) {
        @Override
        public String toString() {
            return "入库托盘索取";
        }
    },
    PALLET_CONFIRMED(62) {
        @Override
        public String toString() {
            return "入库托盘确认";
        }
    },
    PUTAWAY_OBTAINED(63) {
        @Override
        public String toString() {
            return "入库上架索取";
        }
    },
    PUTAWAY_CONFIRMED(64) {
        @Override
        public String toString() {
            return "入库上架确认";
        }
    },
    TERMINATED(99) {
        @Override
        public String toString() {
            return "作业终止";
        }
    };

    CommandStatus(int ordinal) {
    }
}