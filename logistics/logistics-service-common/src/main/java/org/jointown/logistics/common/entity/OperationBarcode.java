package org.jointown.logistics.common.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class OperationBarcode extends Barcode {
    private BarcodeType barcodeType;//条码类型

    private String deviceNo;//设备编号

    private TransferlineSign transferlineSign;//输送线标识

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_ob_bh"))
    private BillHeader billHeader;//单据抬头

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_ob_tb"))
    private TaskBill taskBill;//任务单

    private String groupageNo;//拼箱号

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_ob_sts"))
    private SorterSlide sorterSlide;//分拣滑道

    private boolean available;//是否可用

    private boolean collected;//是否集货

    private GroupageMode groupageMode;//拼箱方式

    @ManyToMany
    @JoinTable(
            foreignKey = @ForeignKey(name = "fk_ob_cmd"),
            inverseForeignKey = @ForeignKey(name = "fk_ob_bar")
    )
    private Set<? extends Command> commands;//指令
}