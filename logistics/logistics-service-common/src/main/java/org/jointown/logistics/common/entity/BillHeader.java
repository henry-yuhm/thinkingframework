package org.jointown.logistics.common.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@MappedSuperclass
public abstract class BillHeader implements Serializable {
    @Id
    @GeneratedValue
    private long id;

    private String no;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_warehouse"))
    private Warehouse warehouse;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_owner"))
    private Owner owner;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_customer"))
    private Customer customer;

    private Type type;

    private Category category;

    private Stage stage;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<? extends BillDetail> billDetails;

    public enum Type {
        INBOUND("IB", 1),
        SALE_RETURN("SR", 4),
        OUTBOUND("OB", 2),
        PURCHASE_RETURN("PR", 3),
        INITIATIVE_REPLENISHMENT("IR", 5),
        EMERGENCY_REPLENISHMENT("ER", 6),
        LOCATION_MOVE("LM", 7),
        LOCATION_ADJUST("LA", 8);

        private final String name;

        private final int ordinal;

        Type(String name, int ordinal) {
            this.name = name;
            this.ordinal = ordinal;
        }
    }

    public enum Category {
        WESTERN_MEDICINE("1", 1),//西药
        TRADITIONAL_CHINESE_MEDICINE("2", 2),//中药
        FAMILY_PLANNING("3", 3),//计生
        APPLIANCE("4", 4),//器械
        RAW_MATERIAL("5", 5),//原料药
        THIRD_PARTY("6", 6),//第三方
        GIFT("7", 7);//赠品

        private final String name;

        private final int ordinal;

        Category(String name, int ordinal) {
            this.name = name;
            this.ordinal = ordinal;
        }
    }

    public enum Stage {
        ORDER_GENERATED("00", 0),
        INIT_FINISHED("C1", 11),
        WAVE_ARRANGED("D1", 21),
        WAVE_RELEASED("D2", 22),
        STAGINGAREA_ALLOCATION_FINISHED("F1", 31),
        STOCKLESSNESS_SUSPENDED("F2", 32),
        STOCKLESSNESS_RELEASED("F3", 33),
        LOT_ALLOCATION_FINISHED("F4", 34),
        TASKS_BALING_FINISHED("F5", 35),
        SPLITING_FINISHED("F6", 36),
        OPERATION_AVAILABLE("J1", 41),
        ON_WORKING("J2", 42),
        INNER_RECHECK_FINISHED("N3", 51),
        ON_OUTER_RECHECKING("W1", 61),
        OUTER_RECHECK_FINISHED("W2", 62),
        OPERATION_FINISHED("ZZ", 99);

        private final String name;

        private final int ordinal;

        Stage(String name, int ordinal) {
            this.name = name;
            this.ordinal = ordinal;
        }
    }
}