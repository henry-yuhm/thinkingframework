package org.thinking.logistics.services.core.entity;

import lombok.Data;
import org.thinking.logistics.services.core.domain.support.TWFKind;
import org.thinking.logistics.services.core.domain.support.TransferlineKind;

import javax.persistence.*;
import java.util.Set;

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

    @OneToMany
    @JoinTable(joinColumns = @JoinColumn(name = "Warehouse_id"), inverseJoinColumns = @JoinColumn(name = "kind_id"))
    private Set<TransferlineKind> transferlineKinds;//输送线类型

    @OneToMany
    @JoinTable(joinColumns = @JoinColumn(name = "Warehouse_id"), inverseJoinColumns = @JoinColumn(name = "kind_id"))
    private Set<TWFKind> twfKinds;//立体库类型

    private String address;//地址
}