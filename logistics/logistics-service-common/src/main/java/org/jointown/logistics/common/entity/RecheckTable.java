package org.jointown.logistics.common.entity;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

@Entity
public class RecheckTable {
    @Id
    @TableGenerator(name = "recheckTableId", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "recheckTableId")
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_rct_wh"))
    private Warehouse warehouse;

    private String tableNo;

    private TableType tableType;

    private TableCategory tableCategory;

    private boolean available;

    private boolean locking;

    private String bufferNo;

    private BufferType bufferType;

    private boolean automatic;

    private String slideNo;

    @Column(nullable = false)
    private BigInteger workload = BigInteger.ZERO;

    @Column(nullable = false)
    private BigInteger goodsNumber = BigInteger.ZERO;

    private String taskBillNo;

    private String splitBillNo;

    private Date updateTime;

    public enum BufferType {
        ;

        private final String name;

        private final int ordinal;

        BufferType(String name, int ordinal) {
            this.name = name;
            this.ordinal = ordinal;
        }
    }

    public enum TableCategory {
        ;

        private final String name;

        private final int ordinal;

        TableCategory(String name, int ordinal) {
            this.name = name;
            this.ordinal = ordinal;
        }
    }

    public enum TableType {
        ;

        private final String name;

        private final int ordinal;

        TableType(String name, int ordinal) {
            this.name = name;
            this.ordinal = ordinal;
        }
    }
}