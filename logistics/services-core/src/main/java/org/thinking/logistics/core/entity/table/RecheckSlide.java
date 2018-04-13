package org.thinking.logistics.core.entity.table;

import lombok.Data;
import org.thinking.logistics.core.entity.Warehouse;

import javax.persistence.*;

@Entity
@Data
public class RecheckSlide {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Warehouse warehouse;//仓库

    @Column(nullable = false)
    private String number;//编号

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private RecheckTable table;//复核台
}