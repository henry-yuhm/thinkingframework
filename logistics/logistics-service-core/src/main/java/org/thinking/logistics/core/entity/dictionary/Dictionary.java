package org.thinking.logistics.core.entity.dictionary;

import javax.persistence.*;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Dictionary {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String number;//编号

    @Column(nullable = false)
    private String name;//名称

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
}