package org.thinking.logistics.services.core.domain.barcode;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.services.core.domain.BaseDomainEntity;

import javax.persistence.Column;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import java.time.Instant;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class Barcode extends BaseDomainEntity {
    @Column(nullable = false, length = 20)
    private String no;//编号

    @Column(nullable = false)
    private Instant creationTime = Instant.now();//创建时间

    @Column(nullable = false)
    private Instant modificationTime = Instant.now();//修改时间
}