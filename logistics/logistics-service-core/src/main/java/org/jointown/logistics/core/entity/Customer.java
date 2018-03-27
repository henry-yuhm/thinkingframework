package org.jointown.logistics.core.entity;

import org.jointown.logistics.core.entity.support.BatchRequest;
import org.jointown.logistics.core.entity.support.CustomerCategory;
import org.jointown.logistics.core.entity.support.CustomerSign;

import javax.persistence.*;

@Entity
@Table(schema = "wms")
public class Customer {
    @Id
    @GeneratedValue
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @Column(nullable = false)
    private Owner owner;//业主

    @Column(nullable = false)
    private String no;//编号

    @Column(nullable = false)
    private String sourceCode;//源编码

    @OneToOne(fetch = FetchType.LAZY)
    private Customer parent;//父客户

    private String mnemonicCode;//助记码

    @Column(nullable = false)
    private String name;//名称

    private String shortName;//简称

    private String address;//地址

    private String phone;//电话

    @Column(nullable = false)
    private CustomerSign sign;//标识

    private String identifier;//识别

    private String seat;//所在地

    private BatchRequest batchRequest;//批号要求

    private CustomerCategory category;//类别

    private String grade;//等级

    private String district;//地区

    private String businessman;//业务员

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    public Customer getParent() {
        return parent;
    }

    public void setParent(Customer parent) {
        this.parent = parent;
    }

    public String getMnemonicCode() {
        return mnemonicCode;
    }

    public void setMnemonicCode(String mnemonicCode) {
        this.mnemonicCode = mnemonicCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public CustomerSign getSign() {
        return sign;
    }

    public void setSign(CustomerSign sign) {
        this.sign = sign;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public BatchRequest getBatchRequest() {
        return batchRequest;
    }

    public void setBatchRequest(BatchRequest batchRequest) {
        this.batchRequest = batchRequest;
    }

    public CustomerCategory getCategory() {
        return category;
    }

    public void setCategory(CustomerCategory category) {
        this.category = category;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getBusinessman() {
        return businessman;
    }

    public void setBusinessman(String businessman) {
        this.businessman = businessman;
    }
}