package org.thinking.sce.service.core.domain.barcode;

import lombok.*;
import org.thinking.sce.service.core.domain.BaseDomainEntity;

import javax.persistence.*;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class Barcode extends BaseDomainEntity {
    @Column(nullable = false, updatable = false, length = 20)
    private String no;//编号
}