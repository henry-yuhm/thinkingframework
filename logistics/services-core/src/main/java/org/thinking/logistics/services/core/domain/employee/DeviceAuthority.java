package org.thinking.logistics.services.core.domain.employee;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.thinking.logistics.services.core.domain.BaseDomainEntity;

import javax.persistence.Column;
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