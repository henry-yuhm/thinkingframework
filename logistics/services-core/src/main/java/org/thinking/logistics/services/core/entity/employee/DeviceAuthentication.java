package org.thinking.logistics.services.core.entity.employee;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.thinking.logistics.services.core.domain.support.OperationDevice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class DeviceAuthentication {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private OperationDevice device;//操作设备

    @Column(nullable = false)
    private String jobNo;//工号

    @Column(nullable = false)
    private String password;//密码
}