package org.thinking.logistics.statemachine.dispatcher.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.statemachine.data.jpa.JpaRepositoryState;

import javax.persistence.*;

@Entity
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