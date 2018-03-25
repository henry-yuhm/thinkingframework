package org.jointown.logistics.common.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class BatchNumber {
    @Id
    @TableGenerator(name = "BatchNumberId", table = "BatchNumberId", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "BatchNumberId")
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_batchnumber_owner"))
    private Owner owner;//业主

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_batchnumber_goods"))
    private Goods goods;//商品

    private String no;//编码

    private Date productionDate;//生产日期

    private Date validUntil;//有效期至

    private String printProductionDate;//打印生产日期

    private String printValidUntil;//打印有效期至

    private String approvalNo;//批准文号

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