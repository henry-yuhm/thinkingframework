package org.jointown.logistics.core.entity.support;

public enum TaskStage {
    CREATED("00", 0),//指令刚生成
    TASK_READY("01", 11),//可以作业
    REPLENISHMENT_DOWNSHELF_OBTAIN("B1", 21),//补货下架索取
    REPLENISHMENT_DOWNSHELF_CONFIRM("B2", 22),//补货下架确认
    REPLENISHMENT_UPSHELF_OBTAIN("B3", 23),//补货上架索取
    REPLENISHMENT_UPSHELF_CONFIRM("B4", 24),//补货上架确认
    TASKBILL_CREATED("C2", 31),//生成分配单
    WHOLEBILL_OBTAIN("C3", 32),//整单索取
    PICKING_OBTAIN("C4", 33),//索取
    PICKING_CONFIRM("C5", 34),//确认
    INNER_RECHECK_OBTAIN("C6", 35),//内复核任务索取（分配单级）
    INNER_RECHECK_GOODSOBTAIN("C7", 36),//内复核商品索取（商品级）
    INNER_RECHECK_CONFIRM("C8", 37),//内复核确认(整件确认)
    OUTER_RECHECK_OBTAIN("CA", 41),//外复核索取
    OUTER_RECHECK_CONFIRM("CB", 42),//外复核确认
    VEHICLE_LOADING_OBTAIN("CC", 51),//装车任务索取
    VEHICLE_LOADING_CONFIRM("CD", 52),//装车任务确认
    VEHICLE_LOADING_RECHECK_OBTAIN("CE", 53),//装车复核索取
    VEHICLE_LOADING_RECHECK_CONFIRM("CF", 54),//装车复核确认
    VEHICLE_LOADING_INFORMATION_CONFIRM("CG", 55),//装车信息确认
    DELIVERY_BACK_CONFIRM("CH", 56),//配送返回确认
    INBOUND_PALLET_OBTAIN("R1", 61),//入库托盘索取
    INBOUND_PALLET_CONFIRM("R2", 62),//入库托盘确认
    INBOUND_UPSHELF_OBTAIN("R3", 63),//入库上架索取
    INBOUND_UPSHELF_CONFIRM("R4", 64),//入库上架确认
    TERMINATED("ZZ", 99);//作业终止

    TaskStage(String name, int ordinal) {
    }
}