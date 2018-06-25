package org.thinking.logistics.services.core.domain.core;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.thinking.logistics.services.core.domain.BaseDomainEntity;

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

    private String description;//地址

    private int loadingOrder = 0;//装车顺序

    private String code;//编码

    @Column(nullable = false)
    private boolean defaults;//默认地址

    private String contactName;//联系人

    private String contactPhone;//联系电话
}