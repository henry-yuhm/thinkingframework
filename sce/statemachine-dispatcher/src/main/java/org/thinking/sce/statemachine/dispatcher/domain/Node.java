package org.thinking.sce.statemachine.dispatcher.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.statemachine.data.jpa.JpaRepositoryState;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

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