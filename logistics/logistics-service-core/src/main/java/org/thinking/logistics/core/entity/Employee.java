package org.thinking.logistics.core.entity;

import org.thinking.logistics.core.domain.support.DeviceAuthority;
import org.thinking.logistics.core.domain.support.OperationDevice;
import org.thinking.logistics.core.entity.dictionary.EmployeePost;
import org.thinking.logistics.core.entity.dictionary.EmployeeRole;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(schema = "wms", uniqueConstraints = @UniqueConstraint(name = "uk_employee", columnNames = {"owner_id", "number"}))
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
    private boolean activated = false;//是否激活

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

    public Employee() {
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMnemonicCode() {
        return mnemonicCode;
    }

    public void setMnemonicCode(String mnemonicCode) {
        this.mnemonicCode = mnemonicCode;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public Set<EmployeeRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<EmployeeRole> roles) {
        this.roles = roles;
    }

    public Set<EmployeePost> getPosts() {
        return posts;
    }

    public void setPosts(Set<EmployeePost> posts) {
        this.posts = posts;
    }

    public Set<Authentication> getAuthentications() {
        return authentications;
    }

    public void setAuthentications(Set<Authentication> authentications) {
        this.authentications = authentications;
    }

    public Set<DeviceAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<DeviceAuthority> authorities) {
        this.authorities = authorities;
    }

    public Set<Owner> getOwners() {
        return owners;
    }

    public void setOwners(Set<Owner> owners) {
        this.owners = owners;
    }

    @Embeddable
    public static class Authentication {
        @Column(nullable = false)
        private OperationDevice device;//操作设备

        @Column(nullable = false)
        private String jobNumber;//工号

        @Column(nullable = false)
        private String password;//密码

        public OperationDevice getDevice() {
            return device;
        }

        public void setDevice(OperationDevice device) {
            this.device = device;
        }

        public String getJobNumber() {
            return jobNumber;
        }

        public void setJobNumber(String jobNumber) {
            this.jobNumber = jobNumber;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}