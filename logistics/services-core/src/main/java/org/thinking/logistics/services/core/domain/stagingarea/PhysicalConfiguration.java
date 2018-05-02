package org.thinking.logistics.services.core.domain.stagingarea;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.thinking.logistics.services.core.domain.Owner;
import org.thinking.logistics.services.core.domain.Warehouse;
import org.thinking.logistics.services.core.domain.support.BillCategory;
import org.thinking.logistics.services.core.domain.support.StagingareaType;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(name = "uk_physical_sga_cfg", columnNames = {"warehouse_id", "owner_id", "billCategory"}))
@Data
@NoArgsConstructor
public class PhysicalConfiguration {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Warehouse warehouse;//仓库

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Owner owner;//业主

    @Column(nullable = false)
    private BillCategory billCategory;//单据类别

    @Column(nullable = false)
    private StagingareaType stagingareaType;//月台类型
}