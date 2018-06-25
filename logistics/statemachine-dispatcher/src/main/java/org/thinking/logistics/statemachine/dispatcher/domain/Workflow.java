package org.thinking.logistics.statemachine.dispatcher.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Set;

@Entity
@DynamicInsert
@DynamicUpdate
@Data
public class Workflow {
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