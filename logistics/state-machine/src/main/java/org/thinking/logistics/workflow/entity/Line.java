package org.thinking.logistics.workflow.entity;

import lombok.Data;
import org.springframework.statemachine.data.jpa.JpaRepositoryTransition;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(Line.LinePk.class)
@Data
public class Line implements Serializable {
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
    public static class LinePk implements Serializable {
        private String source;

        private String target;
    }
}