package org.jointown.logistics.common.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class OperationBarcode extends Barcode {
    private BarcodeType barcodeType;

    private String deviceNo;

    private TransferlineSign transferlineSign;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_ob_bh"))
    private BillHeader billHeader;

    private String taskBillNo;

    private String groupageNo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_ob_sts"))
    private SorterSlide sorterSlide;

    private boolean available;

    private boolean collected;

    private GroupageMode groupageMode;

    @ManyToMany
    @JoinTable(
            foreignKey = @ForeignKey(name = "fk_ob_cmd"),
            inverseForeignKey = @ForeignKey(name = "fk_ob_bar")
    )
    private Set<? extends Command> commands;
}