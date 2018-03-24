package org.jointown.logistics.common.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class StagingArea {
    @Id
    @GeneratedValue
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_stagingarea_warehouse"))
    private Warehouse warehouse;//仓库

    private String no;//编号

    private String name;//名称

    private Type type;//类型

    private Category category;//类别

    private boolean available;//是否可用

    @ManyToMany
    @JoinTable(
            foreignKey = @ForeignKey(name = "fk_stagingarea_owners_sga"),
            inverseForeignKey = @ForeignKey(name = "fk_stagingarea_owners_own")
    )
    private Set<Owner> owners;//业主

    private Command.BusinessType businessType;//业务类型

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_stagingarea_direction"))
    private Direction direction;//方向

    private BillHeader.TakeMode takeMode;//提货方式

    public enum Type {
        NORMAL("1", 1),//普通
        APPENDANT_PICKING("2", 2),//追加拣货
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