package org.thinking.logistics.services.core.entity;

import lombok.Data;
import org.thinking.logistics.services.core.domain.support.DeviceAuthority;
import org.thinking.logistics.services.core.domain.support.OperationDevice;
import org.thinking.logistics.services.core.entity.dictionary.EmployeePost;
import org.thinking.logistics.services.core.entity.dictionary.EmployeeRole;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(schema = "wms", uniqueConstraints = @UniqueConstraint(name = "uk_employee", columnNames = {"owner_id", "number"}))
@Data
public class Employee {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Owner owner;//业主

    @Column(nullable = false, length = 50)
    private String number;//编号

    @Column(nullable = false)
    private String name;//名称

    private String mnemonicCode;//助记码

    @Column(nullable = false)
    private boolean activated = false;//激活

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "employee_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<EmployeeRole> roles;//角色

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "employee_id"), inverseJoinColumns = @JoinColumn(name = "post_id"))
    private Set<EmployeePost> posts;//岗位

    @Embedded
    private Set<Authentication> authentications;//认证

    private Set<DeviceAuthority> authorities;//设备权限

    @OneToMany
    @JoinTable(joinColumns = @JoinColumn(name = "employee_id"), inverseJoinColumns = @JoinColumn(name = "owner_id"))
    private Set<Owner> owners = new LinkedHashSet<>();//业主

    @Embeddable
    @Data
    public static class Authentication {
        @Column(nullable = false)
        private OperationDevice device;//操作设备

        @Column(nullable = false)
        private String jobNumber;//工号

        @Column(nullable = false)
        private String password;//密码
    }
}