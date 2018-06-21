package org.thinking.logistics.services.core.domain.support;

//指令阶段
public enum CommandStage {
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
    REPLENISHMENT_DOWNSHELF_OBTAIN(21) {
        @Override
        public String toString() {
            return "补货下架索取";
        }
    },
    REPLENISHMENT_DOWNSHELF_CONFIRM(22) {
        @Override
        public String toString() {
            return "补货下架确认";
        }
    },
    REPLENISHMENT_UPSHELF_OBTAIN(23) {
        @Override
        public String toString() {
            return "补货上架索取";
        }
    },
    REPLENISHMENT_UPSHELF_CONFIRM(24) {
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
    WHOLEBILL_OBTAIN(32) {
        @Override
        public String toString() {
            return "整单索取";
        }
    },
    PICKING_OBTAIN(33) {
        @Override
        public String toString() {
            return "拣货索取";
        }
    },
    PICKING_CONFIRM(34) {
        @Override
        public String toString() {
            return "拣货确认";
        }
    },
    INNER_RECHECK_OBTAIN(35) {
        @Override
        public String toString() {
            return "内复核任务索取";
        }
    },
    INNER_RECHECK_ITEMOBTAIN(36) {
        @Override
        public String toString() {
            return "内复核商品索取";
        }
    },
    INNER_RECHECK_CONFIRM(37) {
        @Override
        public String toString() {
            return "内复核确认";
        }
    },
    OUTER_RECHECK_OBTAIN(41) {
        @Override
        public String toString() {
            return "外复核索取";
        }
    },
    OUTER_RECHECK_CONFIRM(42) {
        @Override
        public String toString() {
            return "外复核确认";
        }
    },
    LOADING_OBTAIN(51) {
        @Override
        public String toString() {
            return "装车任务索取";
        }
    },
    LOADING_CONFIRM(52) {
        @Override
        public String toString() {
            return "装车任务确认";
        }
    },
    LOADING_RECHECK_OBTAIN(53) {
        @Override
        public String toString() {
            return "装车复核索取";
        }
    },
    LOADING_RECHECK_CONFIRM(54) {
        @Override
        public String toString() {
            return "装车复核确认";
        }
    },
    LOADING_INFORMATION_CONFIRM(55) {
        @Override
        public String toString() {
            return "装车信息确认";
        }
    },
    DISTRIBUTION_BACK_CONFIRM(56) {
        @Override
        public String toString() {
            return "配送返回确认";
        }
    },
    PALLET_OBTAIN(61) {
        @Override
        public String toString() {
            return "入库托盘索取";
        }
    },
    PALLET_CONFIRM(62) {
        @Override
        public String toString() {
            return "入库托盘确认";
        }
    },
    UPSHELF_OBTAIN(63) {
        @Override
        public String toString() {
            return "入库上架索取";
        }
    },
    UPSHELF_CONFIRM(64) {
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

    CommandStage(int ordinal) {
    }
}