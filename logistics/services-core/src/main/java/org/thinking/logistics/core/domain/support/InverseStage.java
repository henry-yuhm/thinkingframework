package org.thinking.logistics.core.domain.support;

// 冲红阶段
public enum InverseStage {
    DISPATCHING("D", 1),//出库调度
    EXECUTING("E", 2),//购退执行
    SUSPENDING("S", 3),//单据挂起
    PICKING("P", 4),//拣货
    RECHECKING("R", 5);//复核

    InverseStage(String name, int ordinal) {
    }
}