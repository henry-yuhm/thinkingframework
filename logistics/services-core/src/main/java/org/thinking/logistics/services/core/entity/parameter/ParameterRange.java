package org.thinking.logistics.services.core.entity.parameter;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class ParameterRange {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String warehouse;//仓库

    @Column(nullable = false)
    private String value;//值
}