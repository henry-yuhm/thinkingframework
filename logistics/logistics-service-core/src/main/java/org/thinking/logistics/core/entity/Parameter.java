package org.thinking.logistics.core.entity;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class Parameter {
    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true, nullable = false, length = 50)
    private String number;//编号

    @Column(nullable = false)
    private String name;//名称

    @Column(nullable = false)
    private String value;//值

    @Column(nullable = false)
    private String sign;//标识

    @Embedded
    @OneToMany
    private Set<Range> ranges = new LinkedHashSet<>();//值域

    private String remarks;//备注

    public Parameter() {
    }

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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Set<Range> getRanges() {
        return ranges;
    }

    public void setRanges(Set<Range> ranges) {
        this.ranges = ranges;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Embeddable
    public static class Range {
        @Column(nullable = false)
        private String warehouse;//仓库

        @Column(nullable = false)
        private String value;//值

        public String getWarehouse() {
            return warehouse;
        }

        public void setWarehouse(String warehouse) {
            this.warehouse = warehouse;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}