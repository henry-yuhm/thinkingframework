package org.thinking.logistics.services.core.domain.support;

//出库阶段
public enum OutboundStage {
    CREATED("00", 0),//单据生成
    INITIALIZED("C1", 11),//初始化
    ARRANGED("D1", 21),//波次安排
    RELEASED("D2", 22),//下发
    STAGINGAREA_ALLOCATED("F1", 31),//月台分配
    SUSPENDED("F2", 32),//单据挂起
    RESEND("F3", 33),//单据补发
    BATCH_ALLOCATED("F4", 34),//批号分配
    BUNDLED("F5", 35),//打包
    SPLIT("F6", 36),//拆分
    TASK_READY("J1", 41),//任务准备
    WORKING("J2", 42),//正在作业
    TASK_COMPLETE("N3", 51),//任务完成
    RECHECKING("W1", 61),//正在复核
    RECHECK_COMPLETE("W2", 62),//复核完成
    TERMINATED("ZZ", 99);//作业终止

    OutboundStage(String name, int ordinal) {
    }
}