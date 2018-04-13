package org.thinking.logistics.services.core.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Address {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Customer customer;//客户

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Direction direction;//方向

    @ManyToOne(fetch = FetchType.LAZY)
    private Route route;//路线

    private String address;//地址

    private int loadingOrder = 0;//装车顺序

    private String code;//编码

    @Column(nullable = false)
    private boolean defaults;//默认地址

    private String contactName;//联系人

    private String contactPhone;//联系电话
}