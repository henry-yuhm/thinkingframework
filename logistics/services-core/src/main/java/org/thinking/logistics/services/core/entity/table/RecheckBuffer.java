package org.thinking.logistics.services.core.entity.table;

import lombok.Data;
import org.thinking.logistics.services.core.domain.support.RecheckBufferKind;
import org.thinking.logistics.services.core.entity.Warehouse;

import javax.persistence.*;

@Entity
@Data
public class RecheckBuffer {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Warehouse warehouse;//仓库

    @Column(nullable = false)
    private String number;//编号

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private RecheckTable table;//复核台

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private RecheckSlide slide;//复核滑道

    @Column(nullable = false)
    private RecheckBufferKind kind = RecheckBufferKind.NORMAL;//类型

    @Column(nullable = false)
    private boolean available = true;//可用
}