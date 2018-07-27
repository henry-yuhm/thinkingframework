package org.thinking.sce.statemachine.dispatcher.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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