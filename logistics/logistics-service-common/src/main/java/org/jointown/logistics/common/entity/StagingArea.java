package org.jointown.logistics.common.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class StagingArea {
    @Id
    @GeneratedValue
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_warehouse"))
    private Warehouse warehouse;

    private String no;

    private String name;

    private Type type;

    private Category category;

    private boolean locking;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            foreignKey = @ForeignKey(name = "fk_stagingarea_owners_s"),
            inverseForeignKey = @ForeignKey(name = "fk_stagingarea_owners_o")
    )
    private Set<Owner> owners;

    private OperationCommand.BusinessType businessType;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_stagingarea_direction"))
    private Direction direction;

    private BusinessBillHeader.TakeMode takeMode;

    public enum Type {
        NORMAL("1", 1),//普通
        APPEND_PICKING("2", 2),//追加拣货
        TRADITIONAL_CHINESE_MEDICINE("3", 3),//中药
        FAMILY_PLANNING_AND_APPLIANCE("4", 4),//计生器械
        VIRTUAL("5", 5),//虚拟
        THIRD_PARTY("6", 6),//第三方
        RANDOM("7", 7);//机动

        private final String name;

        private final int ordinal;

        Type(String name, int ordinal) {
            this.name = name;
            this.ordinal = ordinal;
        }
    }

    public enum Category {
        TINY_CUSTOMER("1", 1),//小型客户区
        MEDIUM_CUSTOMER("2", 2),//中等客户区
        HEAVY_CUSTOMER("3", 3);//大型客户区

        private final String name;

        private final int ordinal;

        Category(String name, int ordinal) {
            this.name = name;
            this.ordinal = ordinal;
        }
    }
}