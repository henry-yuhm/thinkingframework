package org.jointown.logistics.core.entity.bill;

import org.jointown.logistics.core.entity.support.InboundKind;

import javax.persistence.*;
import java.sql.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class PurchaseOrderHeader extends Header {
    @Column(nullable = false)
    private boolean complete = false;//是否完成

    @Column(nullable = false)
    private InboundKind kind;//入库类型

    @Column(nullable = false)
    private Date invoiceTime;//开票时间

    private String buyer;//采购员

    private String contactName;//联系人

    private String contactPhone;//联系电话

    private String departureLocation;//发货地点

    private String shippingMode;//运输方式

    private String shipper;//运输方

    private Date shippingTime;//运输时间

    private String tempControlMode;//温控方式

    private String tempRecord;//温度记录

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "header_id"), inverseJoinColumns = @JoinColumn(name = "detail_id"))
    private Set<PurchaseOrderDetail> details = new LinkedHashSet<>();//单据明细

    public PurchaseOrderHeader() {
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public InboundKind getKind() {
        return kind;
    }

    public void setKind(InboundKind kind) {
        this.kind = kind;
    }

    public Date getInvoiceTime() {
        return invoiceTime;
    }

    public void setInvoiceTime(Date invoiceTime) {
        this.invoiceTime = invoiceTime;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getDepartureLocation() {
        return departureLocation;
    }

    public void setDepartureLocation(String departureLocation) {
        this.departureLocation = departureLocation;
    }

    public String getShippingMode() {
        return shippingMode;
    }

    public void setShippingMode(String shippingMode) {
        this.shippingMode = shippingMode;
    }

    public String getShipper() {
        return shipper;
    }

    public void setShipper(String shipper) {
        this.shipper = shipper;
    }

    public Date getShippingTime() {
        return shippingTime;
    }

    public void setShippingTime(Date shippingTime) {
        this.shippingTime = shippingTime;
    }

    public String getTempControlMode() {
        return tempControlMode;
    }

    public void setTempControlMode(String tempControlMode) {
        this.tempControlMode = tempControlMode;
    }

    public String getTempRecord() {
        return tempRecord;
    }

    public void setTempRecord(String tempRecord) {
        this.tempRecord = tempRecord;
    }

    @Override
    public Set<PurchaseOrderDetail> getDetails() {
        return details;
    }

    public void setDetails(Set<PurchaseOrderDetail> details) {
        this.details = details;
    }
}