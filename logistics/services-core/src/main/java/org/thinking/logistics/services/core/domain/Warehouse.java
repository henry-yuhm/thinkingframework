package org.thinking.logistics.services.core.domain;

import lombok.Data;
import org.thinking.logistics.services.core.domain.support.TWFType;
import org.thinking.logistics.services.core.domain.support.TransferlineType;

import javax.persistence.*;
import java.util.LinkedHashMap;

@Entity
@Data
public class Warehouse {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Owner owner;//业主

    @Column(nullable = false)
    private String name;//名称

    @Column(nullable = false)
    private boolean electronicLabel;//电子标签

    @Column(nullable = false)
    private boolean pallet;//使用托盘

    @Column(nullable = false)
    private boolean sorter;//有分拣机

    @Column(nullable = false)
    private boolean tablet;//使用平板电脑

    private LinkedHashMap<Integer, TransferlineType> transferlineTypes = new LinkedHashMap<>();//输送线类型

    private LinkedHashMap<Integer, TWFType> twfTypes = new LinkedHashMap<>();//立体库类型

    private String address;//地址
}