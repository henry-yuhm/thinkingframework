package org.jointown.logistics.core.entity.bill;

import org.jointown.logistics.core.entity.Customer;
import org.jointown.logistics.core.entity.Owner;
import org.jointown.logistics.core.entity.Warehouse;
import org.jointown.logistics.core.entity.support.BillCategory;
import org.jointown.logistics.core.entity.support.BillPriority;
import org.jointown.logistics.core.entity.support.BillSign;
import org.jointown.logistics.core.entity.support.BillType;

import javax.persistence.*;
import java.sql.Time;

@MappedSuperclass
public abstract class BillHeader {
    @Id
    @GeneratedValue
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @Column(nullable = false)
    private Warehouse warehouse;//仓库

    @OneToOne(fetch = FetchType.LAZY)
    @Column(nullable = false)
    private Owner owner;//业主

    @OneToOne(fetch = FetchType.LAZY)
    private Customer customer;//客户

    @Column(nullable = false)
    private String no;//单据编号

    @Column(nullable = false)
    private BillType type;//单据类型

    @Column(nullable = false)
    private BillCategory category;//单据类别

    @Column(nullable = false)
    private BillSign sign;//单据标识

    @Column(nullable = false)
    private BillPriority priority;//单据优先级

    @Column(nullable = false)
    private String operator;//操作员

    @Column(nullable = false)
    private Time creationTime;//创建时间

    @Column(nullable = false)
    private Time modificationTime;//修改时间

    private String remarks;//备注

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public BillType getType() {
        return type;
    }

    public void setType(BillType type) {
        this.type = type;
    }

    public BillCategory getCategory() {
        return category;
    }

    public void setCategory(BillCategory category) {
        this.category = category;
    }

    public BillSign getSign() {
        return sign;
    }

    public void setSign(BillSign sign) {
        this.sign = sign;
    }

    public BillPriority getPriority() {
        return priority;
    }

    public void setPriority(BillPriority priority) {
        this.priority = priority;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Time getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Time creationTime) {
        this.creationTime = creationTime;
    }

    public Time getModificationTime() {
        return modificationTime;
    }

    public void setModificationTime(Time modificationTime) {
        this.modificationTime = modificationTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}