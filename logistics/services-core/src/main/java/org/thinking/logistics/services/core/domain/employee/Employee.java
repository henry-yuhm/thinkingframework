package org.thinking.logistics.services.core.domain.employee;

import lombok.Data;
import org.thinking.logistics.services.core.domain.Owner;
import org.thinking.logistics.services.core.domain.dictionary.EmployeePost;
import org.thinking.logistics.services.core.domain.dictionary.EmployeeRole;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(name = "uk_employee", columnNames = {"owner_id", "no"}))
@Data
public class Employee {
    @Id
    @GeneratedValue
    private long id;

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
    private Set<EmployeeRole> roles = new LinkedHashSet<>();//角色

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "employee_id"), inverseJoinColumns = @JoinColumn(name = "post_id"))
    private Set<EmployeePost> posts = new LinkedHashSet<>();//岗位

    @OneToOne(fetch = FetchType.LAZY)
    private DeviceAuthority authority;//设备权限

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(joinColumns = @JoinColumn(name = "employee_id"), inverseJoinColumns = @JoinColumn(name = "authentication_id"))
    private Set<DeviceAuthentication> authentications = new LinkedHashSet<>();//设备认证

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(joinColumns = @JoinColumn(name = "employee_id"), inverseJoinColumns = @JoinColumn(name = "owner_id"))
    private Set<Owner> owners = new LinkedHashSet<>();//业主
}