package org.thinking.sce.statemachine.dispatcher.domain;

import lombok.Data;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.Set;

@Entity
@DynamicInsert
@DynamicUpdate
@Data
public class Workflow {
    @Id
    @Column(length = 50)
    private String id;

    @Column(nullable = false, updatable = false, length = 100)
    private String name;

    private String kind;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Node> nodes;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Line> lines;
}