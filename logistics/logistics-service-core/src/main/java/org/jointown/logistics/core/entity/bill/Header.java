package org.jointown.logistics.core.entity.bill;

import org.jointown.logistics.core.entity.Customer;
import org.jointown.logistics.core.entity.Owner;
import org.jointown.logistics.core.entity.Warehouse;
import org.jointown.logistics.core.entity.support.BillCategory;
import org.jointown.logistics.core.entity.support.BillSign;
import org.jointown.logistics.core.entity.support.BillType;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Set;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Header {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Warehouse warehouse;//仓库

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Owner owner;//业主

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;//客户

    @Column(unique = true, nullable = false, length = 100)
    private String number;//单据编号

    @Column(nullable = false)
    private BillType type;//单据类型

    @Column(nullable = false)
    private BillCategory category;//单据类别

    @Column(nullable = false)
    private BillSign sign;//单据标识

    @Column(nullable = false)
    private String operator;//操作员

    private String businessman;//业务员

    private String buyer;//采购员

    @Column(nullable = false)
    private Date creationTime = Date.valueOf(LocalDate.now());//创建时间

    @Column(nullable = false)
    private Date modificationTime = Date.valueOf(LocalDate.now());//修改时间

    private String remarks;//备注

    public abstract Set<? extends Detail> getDetails();

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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getBusinessman() {
        return businessman;
    }

    public void setBusinessman(String businessman) {
        this.businessman = businessman;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Date getModificationTime() {
        return modificationTime;
    }

    public void setModificationTime(Date modificationTime) {
        this.modificationTime = modificationTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}