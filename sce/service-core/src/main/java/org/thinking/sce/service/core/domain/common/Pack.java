package org.thinking.sce.service.core.domain.common;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.thinking.sce.service.core.domain.BaseDomainEntity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@DynamicInsert
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper = true)
public class Pack extends BaseDomainEntity {
    @Column(unique = true, nullable = false, length = 50)
    private String no;//编号

    @Column(length = 200)
    private String description;//描述

    @Column(nullable = false)
    private BigDecimal threshold;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(joinColumns = @JoinColumn(name = "pack_id"), inverseJoinColumns = @JoinColumn(name = "component_id"))
    private Set<PackComponent> components;//包装成分
}