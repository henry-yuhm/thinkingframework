package org.thinking.logistics.core.entity.barcode;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Barcode {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, length = 20)
    private String number;//编号

    @Column(nullable = false)
    private Date creationTime = Date.valueOf(LocalDate.now());//创建时间

    @Column(nullable = false)
    private Date modificationTime = Date.valueOf(LocalDate.now());//修改时间

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Date getModificationTime() {
        return modificationTime;
    }

    public void setModificationTime(Date modificationTime) {
        this.modificationTime = modificationTime;
    }
}