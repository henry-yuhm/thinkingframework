package org.thinking.sce.service.core.domain.employee;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.thinking.sce.service.core.domain.BaseDomainEntity;
import org.thinking.sce.service.core.domain.support.OperationDevice;

import javax.persistence.Column;
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