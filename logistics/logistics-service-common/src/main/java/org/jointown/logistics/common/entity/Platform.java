package org.jointown.logistics.common.entity;

import javax.persistence.*;

@Entity
public class Platform {
    @Id
    @TableGenerator(name = "platformId", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "platformId")
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_pfm_wh"))
    private Warehouse warehouse;
}