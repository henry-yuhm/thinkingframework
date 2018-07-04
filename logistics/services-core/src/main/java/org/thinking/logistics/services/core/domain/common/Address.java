package org.thinking.logistics.services.core.domain.common;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.thinking.logistics.services.core.domain.BaseDomainEntity;
import org.thinking.logistics.services.core.domain.employee.Employee;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
@DynamicInsert
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper = true)
public class Address extends BaseDomainEntity {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Customer customer;//客户

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Direction direction;//方向

    @ManyToOne(fetch = FetchType.LAZY)
    private Route route;//路线

    @Column(length = 200)
    private String description;//地址

    private int loadingOrder = 0;//装车顺序

    @Column(length = 100)
    private String code;//编码

    @Column(nullable = false)
    private boolean defaults;//默认地址

    @ManyToOne(fetch = FetchType.LAZY)
    private Employee contactName;//联系人

    @Column(length = 100)
    private String contactPhone;//联系电话
}