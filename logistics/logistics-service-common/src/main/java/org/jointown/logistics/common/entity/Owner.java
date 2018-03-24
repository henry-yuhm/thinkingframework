package org.jointown.logistics.common.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Owner {
    @Id
    private long id;

    private String name;//名称

    private String mnemonicCode;//助记吗

    private String stockUpperLimit;//库存上限

    private String serviceHotline;//服务热线

    private boolean thirdpart;//是否第三方

    public Owner() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getStockUpperLimit() {
        return stockUpperLimit;
    }

    public void setStockUpperLimit(String stockUpperLimit) {
        this.stockUpperLimit = stockUpperLimit;
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