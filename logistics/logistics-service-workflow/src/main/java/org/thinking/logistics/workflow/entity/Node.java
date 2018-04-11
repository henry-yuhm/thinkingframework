package org.thinking.logistics.workflow.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.statemachine.data.jpa.JpaRepositoryState;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class Node implements Serializable {
    @Id
    @Column(length = 50)
    @JSONField
    private String id;

    @JSONField(ordinal = 1)
    private String label;

    @Transient
    @JSONField(ordinal = 2)
    private boolean current;

    @JSONField(ordinal = 3)
    private String shape;

    @Embedded
    @JSONField(ordinal = 4)
    private Style style;

    @OneToOne(fetch = FetchType.EAGER)
    @JSONField(ordinal = 5)
    private JpaRepositoryState state;
}