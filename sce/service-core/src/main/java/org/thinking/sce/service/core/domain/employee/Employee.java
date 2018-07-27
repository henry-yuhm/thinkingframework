package org.thinking.sce.service.core.domain.employee;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.thinking.sce.service.core.domain.BaseDomainEntity;
import org.thinking.sce.service.core.domain.common.Owner;
import org.thinking.sce.service.core.domain.dictionary.EmployeePost;
import org.thinking.sce.service.core.domain.dictionary.EmployeeRole;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.Set;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(name = "uk_employee", columnNames = {"owner_id", "no"}))
@DynamicInsert
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper = true)
public class Employee extends BaseDomainEntity {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Owner owner;//业主

    @Column(nullable = false, length = 50)
    private String no;//编号

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

    @OneToOne(fetch = FetchType.LAZY)
    private DeviceAuthority authority;//设备权限

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(joinColumns = @JoinColumn(name = "employee_id"), inverseJoinColumns = @JoinColumn(name = "authentication_id"))
    private Set<DeviceAuthentication> authentications;//设备认证

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(joinColumns = @JoinColumn(name = "employee_id"), inverseJoinColumns = @JoinColumn(name = "owner_id"))
    private Set<Owner> owners;//业主
}