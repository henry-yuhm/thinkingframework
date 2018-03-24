package org.jointown.logistics.common.entity;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public abstract class Barcode {
    @Id
    @TableGenerator(name = "barcodeId", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "barcodeId")
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    private Warehouse warehouse;//仓库

    private String no;//编号

    private Date createTime;//创建时间

    private Date updateTime;//更新时间

    public enum BarcodeType {
        INBOUND("1", 1),//入库
        OUTBOUND("2", 2),//出库
        REPLENISHMENT("3", 3),//补货
        MOVEMENT("4", 4);//移库

        private final String name;

        private final int ordinal;

        BarcodeType(String name, int ordinal) {
            this.name = name;
            this.ordinal = ordinal;
        }
    }

    public enum GroupageMode {
        ;

        private final String name;

        private final int ordinal;

        GroupageMode(String name, int ordinal) {
            this.name = name;
            this.ordinal = ordinal;
        }
    }

    public enum TransferlineSign {
        ;

        private final String name;

        private final int ordinal;

        TransferlineSign(String name, int ordinal) {
            this.name = name;
            this.ordinal = ordinal;
        }
    }
}