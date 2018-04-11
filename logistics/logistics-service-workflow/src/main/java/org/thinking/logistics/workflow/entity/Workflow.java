package org.thinking.logistics.workflow.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
public class Workflow implements Serializable {
    @Id
    @Column(length = 50)
    @JSONField
    private String id;

    @JSONField(ordinal = 1)
    private String name;

    @JSONField(ordinal = 2)
    private String kind;

    @ManyToMany(fetch = FetchType.EAGER)
    @JSONField(ordinal = 3)
    private Set<Node> nodes;

    @ManyToMany(fetch = FetchType.EAGER)
    @JSONField(ordinal = 4)
    private Set<Line> lines;
}