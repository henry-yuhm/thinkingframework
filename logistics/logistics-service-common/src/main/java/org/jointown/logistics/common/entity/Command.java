package org.jointown.logistics.common.entity;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public abstract class Command {
    @Id
    @GeneratedValue
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    private Warehouse warehouse;//仓库

    @OneToOne(fetch = FetchType.LAZY)
    private Owner owner;//业主

    @OneToOne(fetch = FetchType.LAZY)
    private Customer customer;//客户

    @OneToOne(fetch = FetchType.LAZY)
    private Command parentCommand;//父指令

    @OneToOne(fetch = FetchType.LAZY)
    private BillHeader billHeader;//单据抬头

    @OneToOne(fetch = FetchType.LAZY)
    private BillDetail billDetail;//单据明细

    @OneToOne(fetch = FetchType.LAZY)
    private Goods goods;//商品

    @OneToOne(fetch = FetchType.LAZY)
    private BatchNumber batchNumber;//批号

    private PackageSign packageSign;//包装标识

    private BusinessType businessType;//业务类型

    private OperationCategory operationCategory;//作业类别

    private TaskStage taskStage;//任务阶段

    private OperationMode operationMode;//作业方式

    @OneToOne(fetch = FetchType.LAZY)
    private OperationBarcode operationBarcode;//作业条码

    private Date createTime;//创建时间

    private Date updateTime;//更新时间

    public enum AppendixSign {
        ORIGINAL("1", 1),//原始指令
        APPENDING("2", 2),//正在追加
        APPEND("3", 3),//追加指令
        DISAPPEND("4", 4);//不追加

        private final String name;

        private final int ordinal;

        AppendixSign(String name, int ordinal) {
            this.name = name;
            this.ordinal = ordinal;
        }
    }

    public enum BusinessType {
        PURCHASE_INBOUND("1", 1),//购进入库
        SALE_OUTBOUND("2", 2),//销售出库
        PURCHASE_RETURN("3", 3),//购进退出
        SALE_RETURN("4", 4),//销售退回
        REPLENISHMENT("5", 5),//补货
        DEMONSTRATION_OPERATION("6", 6),//演示作业
        LOCATION_MOVEMENT("7", 7),//移库
        PALLET_RETURN("8", 8),//回盘
        MAKE_INVENTORY("9", 9),//盘点
        GIFT_OUTBOUND("10", 10),//赠品出库
        BATCHNUMBER_ADJUST("11", 11);//批号调整

        private final String name;

        private final int ordinal;

        BusinessType(String name, int ordinal) {
            this.name = name;
            this.ordinal = ordinal;
        }
    }

    public enum OperationCategory {
        MAKE_INVENTORY("1", 1),//盘点
        GREEN_CHANNEL("2", 2),//绿色通道
        PURCHASE_RETURN("3", 3),//购进退出
        SELF_SERVICE_OUTBOUND("4", 4),//自提出库
        NORMAL_OUTBOUND("5", 5),//普通出库
        PASSIVITY_REPLENISHMENT("6", 6),//被动补货
        INITIATIVE_REPLENISHMENT("7", 7),//主动补货
        INBOUND_UPSHELF("9", 9),//入库上架
        LOCATION_MOVEMENT("10", 10),//移库
        GIFT_OUTBOUND("11", 11),//赠品出库
        BATCHNUMBER_ADJUST("12", 12);//批号调整

        private final String name;

        private final int ordinal;

        OperationCategory(String name, int ordinal) {
            this.name = name;
            this.ordinal = ordinal;
        }
    }

    public enum OperationMode {
        LABEL("1", 1),//标签作业
        PAPER("2", 2),//纸单作业
        PDA("3", 3),//PDA作业
        DPS("4", 4),//DPS作业
        TPC("5", 5),//平板作业
        PILER("6", 6);//堆垛机作业

        private final String name;

        private final int ordinal;

        OperationMode(String name, int ordinal) {
            this.name = name;
            this.ordinal = ordinal;
        }
    }

    public enum PackageSign {
        WHOLE_PIECES("1", 1),
        REMAINDER("2", 2);

        private final String name;

        private final int ordinal;

        PackageSign(String name, int ordinal) {
            this.name = name;
            this.ordinal = ordinal;
        }
    }

    public enum TaskStage {
        COMMAND_CREATE("00", 0),//指令刚生成
        OPERATION_AVAILABLE("01", 11),//可以作业
        REPLENISHMENT_DOWNSHELF_OBTAIN("B1", 21),//补货下架索取
        REPLENISHMENT_DOWNSHELF_CONFIRM("B2", 22),//补货下架确认
        REPLENISHMENT_UPSHELF_OBTAIN("B3", 23),//补货上架索取
        REPLENISHMENT_UPSHELF_CONFIRM("B4", 24),//补货上架确认
        TASKBILL_CREATE("C2", 31),//生成分配单
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
        OPERATION_COMPLETE("ZZ", 99);//作业终止

        private final String name;

        private final int ordinal;

        TaskStage(String name, int ordinal) {
            this.name = name;
            this.ordinal = ordinal;
        }
    }
}