package org.thinking.sce.service.core.domain.employee;

import lombok.*;
import org.hibernate.annotations.*;
import org.thinking.sce.service.core.domain.BaseDomainEntity;
import org.thinking.sce.service.core.domain.support.OperationDevice;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@DynamicInsert
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class DeviceAuthentication extends BaseDomainEntity {
    @Column(nullable = false)
    private OperationDevice device;//操作设备

    @Column(nullable = false)
    private String jobNo;//工号

    @Column(nullable = false)
    private String password;//密码
}