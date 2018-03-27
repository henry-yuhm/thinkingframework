package org.jointown.logistics.core.entity.barcode;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.sql.Time;

@MappedSuperclass
public abstract class Barcode {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String no;//编号

    @Column(nullable = false)
    private Time creationTime;//创建时间

    @Column(nullable = false)
    private Time modificationTime;//修改时间

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public Time getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Time creationTime) {
        this.creationTime = creationTime;
    }

    public Time getModificationTime() {
        return modificationTime;
    }

    public void setModificationTime(Time modificationTime) {
        this.modificationTime = modificationTime;
    }
}