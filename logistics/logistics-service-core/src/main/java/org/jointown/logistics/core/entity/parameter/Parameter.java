package org.jointown.logistics.core.entity.parameter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Parameter {
    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true, nullable = false, length = 50)
    private String number;//编号

    @Column(nullable = false)
    private String name;//名称

    @Column(nullable = false)
    private String sign;//标识

    private String remarks;//备注

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

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}