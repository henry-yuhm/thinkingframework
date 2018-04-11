package org.thinking.logistics.core.entity.barcode;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.core.entity.SorterSlide;

import javax.persistence.*;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class OutboundBarcode extends TaskBarcode {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private SorterSlide slide;//分拣滑道

    @Column(nullable = false)
    private boolean available = true;//是否可用

    @Column(nullable = false)
    private boolean collected = false;//是否集货
}