package org.jointown.logistics.common.entity;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public abstract class Barcode {
    @Id
    @TableGenerator(name = "BarcodeId", table = "BarcodeId", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "barcodeId")
    protected long id;

    @OneToOne(fetch = FetchType.LAZY)
    protected Warehouse warehouse;//仓库

    protected String no;//编号

    protected Date creationTime;//创建时间

    protected Date modificationTime;//修改时间
}