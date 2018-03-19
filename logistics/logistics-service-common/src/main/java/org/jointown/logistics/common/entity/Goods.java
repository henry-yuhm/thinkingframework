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

    private String splitGranularity;

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

    }

    public enum Classification {
        A,
        B,
        C
    }

    public enum StorageSign {

    }
}