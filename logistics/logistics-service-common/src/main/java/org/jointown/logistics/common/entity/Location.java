package org.jointown.logistics.common.entity;

import javax.persistence.*;

@Entity
public class Location {
    @Id
    @TableGenerator(name = "LocationId", table = "LocationId", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "LocationId")
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_location_warehouse"))
    private Warehouse warehouse;//仓库
}