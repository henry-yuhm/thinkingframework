package org.jointown.logistics.core.entity.barcode;

import javax.persistence.*;
import java.sql.Time;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Barcode {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, length = 20)
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