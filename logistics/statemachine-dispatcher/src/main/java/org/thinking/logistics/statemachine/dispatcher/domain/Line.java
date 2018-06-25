package org.thinking.logistics.statemachine.dispatcher.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.statemachine.data.jpa.JpaRepositoryTransition;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@DynamicInsert
@DynamicUpdate
@IdClass(Line.PrimaryKey.class)
@Data
public class Line {
    @Id
    @Column(length = 50)
    private String source;

    @Id
    @Column(length = 50)
    private String target;

    private String label;

    @OneToOne
    private JpaRepositoryTransition transition;

    @Data
    public static class PrimaryKey implements Serializable {
        private String source;

        private String target;
    }
}