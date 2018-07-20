package org.thinking.sce.statemachine.dispatcher.domain;

import lombok.*;
import org.hibernate.annotations.*;
import org.springframework.statemachine.data.jpa.JpaRepositoryState;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@DynamicInsert
@DynamicUpdate
@Data
@NoArgsConstructor
public class Node {
    @Id
    @Column(length = 50)
    private String id;

    private String label;

    @Transient
    private boolean current;

    private String shape;

    @Embedded
    private Style style;

    @OneToOne
    private JpaRepositoryState state;
}