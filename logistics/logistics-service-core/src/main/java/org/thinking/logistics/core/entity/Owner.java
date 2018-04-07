package org.thinking.logistics.core.entity;

import javax.persistence.*;

@Entity
@Table(schema = "wms")
public class Owner {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String name;//名称

    private String mnemonicCode;//助记码

    @Column(nullable = false)
    private String inventoryUpper;//库存上限

    private String serviceHotline;//服务热线

    @Column(nullable = false)
    private boolean thirdpart;//是否第三方

    public Owner() {
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

    public String getInventoryUpper() {
        return inventoryUpper;
    }

    public void setInventoryUpper(String inventoryUpper) {
        this.inventoryUpper = inventoryUpper;
    }

    public String getServiceHotline() {
        return serviceHotline;
    }

    public void setServiceHotline(String serviceHotline) {
        this.serviceHotline = serviceHotline;
    }

    public boolean isThirdpart() {
        return thirdpart;
    }

    public void setThirdpart(boolean thirdpart) {
        this.thirdpart = thirdpart;
    }
}