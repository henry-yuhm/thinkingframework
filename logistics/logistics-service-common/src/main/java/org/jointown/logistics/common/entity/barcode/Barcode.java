package org.jointown.logistics.common.entity.barcode;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.sql.Time;

@MappedSuperclass
public abstract class Barcode {
    @Id
    @GeneratedValue
    private long id;

    private String no;//编号

    private Time creationTime;//创建时间

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