package org.thinking.logistics.workflow.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
public class Workflow implements Serializable {
    @Id
    @Column(length = 50)
    private String id;

    private String name;

    private String kind;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Node> nodes;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Line> lines;
}