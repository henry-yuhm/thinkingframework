package org.thinking.sce.service.core.domain.employee;

import lombok.*;
import org.hibernate.annotations.*;
import org.thinking.sce.service.core.domain.BaseDomainEntity;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@DynamicInsert
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class DeviceAuthority extends BaseDomainEntity {
    @Column(nullable = false)
    private boolean picking = false;//拣货

    @Column(nullable = false)
    private boolean gathering = false;//集货

    @Column(nullable = false)
    private boolean reviewing = false;//复核
}