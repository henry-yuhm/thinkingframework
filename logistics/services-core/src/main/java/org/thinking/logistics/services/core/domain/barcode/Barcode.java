package org.thinking.logistics.services.core.domain.barcode;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.services.core.domain.BaseDomainEntity;

import javax.persistence.Column;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import java.sql.Date;
import java.time.LocalDate;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class Barcode extends BaseDomainEntity {
    @Column(nullable = false, length = 20)
    private String no;//编号

    @Column(nullable = false)
    private Date creationTime = Date.valueOf(LocalDate.now());//创建时间

    @Column(nullable = false)
    private Date modificationTime = Date.valueOf(LocalDate.now());//修改时间
}