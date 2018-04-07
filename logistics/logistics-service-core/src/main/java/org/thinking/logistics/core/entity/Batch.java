package org.thinking.logistics.core.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(schema = "wms")
public class Batch {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Owner owner;//业主

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Goods goods;//商品

    @Column(nullable = false)
    private String number;//编码

    @Column(nullable = false)
    private Date productionDate;//生产日期

    @Column(nullable = false)
    private Date validUntil;//有效期至

    private String printProductionDate;//打印生产日期

    private String printValidUntil;//打印有效期至

    private String approvalNo;//批准文号

    public Batch() {
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }

    public Date getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Date validUntil) {
        this.validUntil = validUntil;
    }

    public String getPrintProductionDate() {
        return printProductionDate;
    }

    public void setPrintProductionDate(String printProductionDate) {
        this.printProductionDate = printProductionDate;
    }

    public String getPrintValidUntil() {
        return printValidUntil;
    }

    public void setPrintValidUntil(String printValidUntil) {
        this.printValidUntil = printValidUntil;
    }

    public String getApprovalNo() {
        return approvalNo;
    }

    public void setApprovalNo(String approvalNo) {
        this.approvalNo = approvalNo;
    }
}