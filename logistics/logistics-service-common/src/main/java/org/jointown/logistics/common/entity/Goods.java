package org.jointown.logistics.common.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Goods {
    @Id
    @GeneratedValue
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_goods_owner"))
    private Owner owner;

    private String no;

    private String name;

    private String universalName;

    private String mnemonicCode;

    private String specification;

    private String manufacturer;

    private String producingArea;

    private int fullloadPackageQuantity;

    private int mediumPackageQuantity;

    private int tinyPackageQuantity;

    private String packageUnit;

    private String approvalNo;

    private Date approvalNoValidUntil;

    private SplitGranularity splitGranularity;

    private int minInvoiceUnit;

    private String category;

    private String fullloadPackageBarcode;

    private String mediumPackageBarcode;

    private String tinyPackageBarcode;

    private Classification fullloadClassification;

    private Classification ScatterClassification;

    private BigDecimal length;

    private BigDecimal width;

    private BigDecimal height;

    private BigDecimal fullloadPackageVolume;

    private BigDecimal mediumPackageVolume;

    private BigDecimal tinyPackageVolume;

    private BigDecimal goodsWeight;

    private BigDecimal packageWeight;

    private StorageSign storageSign;

    private String storageCondition;

    private String storageRequest;

    public enum SplitGranularity {
        SCATTER_AVAILABLE("1", 1),
        UNSPLIT_MEDIUM_PACKAGE("2", 2),
        UNSPLIT_FULLLOAD_PACKAGE("3", 3),
        DECIMAL_AVAILABLE("4", 4);

        private final String name;

        private final int ordinal;

        SplitGranularity(String name, int ordinal) {
            this.name = name;
            this.ordinal = ordinal;
        }
    }

    public enum Classification {
        A("A", 1),
        B("B", 2),
        C("C", 3);

        private final String name;

        private final int ordinal;

        Classification(String name, int ordinal) {
            this.name = name;
            this.ordinal = ordinal;
        }
    }

    public enum StorageSign {
        ALL_PACKAGE("1", 1),
        SCATTER_PACKAGE("2", 2);

        private final String name;

        private final int ordinal;

        StorageSign(String name, int ordinal) {
            this.name = name;
            this.ordinal = ordinal;
        }
    }
}