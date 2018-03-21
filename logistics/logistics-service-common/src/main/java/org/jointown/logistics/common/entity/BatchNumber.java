package org.jointown.logistics.common.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class BatchNumber {
    @Id
    @GeneratedValue
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    private Owner owner;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_batchnumber_goods"))
    private Goods goods;

    private String no;

    private Date productionDate;

    private Date validUntil;

    private String printProductionDate;

    private String printValidUntil;

    private String approvalNo;

    public BatchNumber() {
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

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
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