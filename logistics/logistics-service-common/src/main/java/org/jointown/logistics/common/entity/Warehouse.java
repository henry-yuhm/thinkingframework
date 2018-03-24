package org.jointown.logistics.common.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Warehouse {
    @Id
    @TableGenerator(name = "WarehouseId", table = "WarehouseId", initialValue = 1000, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "WarehouseId")
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_wh_own"))
    private Owner owner;//业主

    private String name;//名称

    private boolean electronicLabel;//是否有电子标签

    private boolean pallet;//是否使用托盘

    private boolean sorter;//是否有分拣机

    private boolean tablet;//是否使用平板电脑

    private Set<TransferlineSign> transferlineSigns;//输送线标识

    private Set<TWFSign> twfSigns;//立体库标识

    public enum TransferlineSign {
        ;

        private final String name;

        private final int ordinal;

        TransferlineSign(String name, int ordinal) {
            this.name = name;
            this.ordinal = ordinal;
        }
    }

    public enum TWFSign {
        ;

        private final String name;

        private final int ordinal;

        TWFSign(String name, int ordinal) {
            this.name = name;
            this.ordinal = ordinal;
        }
    }
}