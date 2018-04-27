package org.thinking.logistics.services.core.entity.employee;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class DeviceAuthority {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private boolean picking = false;//拣货

    @Column(nullable = false)
    private boolean gathering = false;//集货

    @Column(nullable = false)
    private boolean rechecking = false;//复核
}